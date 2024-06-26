package com.example.viettravel_backend.auth;

import com.example.viettravel_backend.config.JwtService;
import com.example.viettravel_backend.entity.enums.Role;
import com.example.viettravel_backend.entity.User;
import com.example.viettravel_backend.exception.ParamInvalidException;
import com.example.viettravel_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequest request) {
        var user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .balance((long)10000000)
                .build();
        userRepository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationResquest request) throws ResponseStatusException {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ParamInvalidException("Tài khoản chưa được đăng kí"));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new ParamInvalidException("Mật khẩu không chính xác");
        }
        var accessToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    public void resetPassword(ResetPasswordRequest request) throws ResponseStatusException {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ParamInvalidException("Tên đăng nhập không tồn tại"));
        if(!user.getEmail().equals(request.getEmail())) {
            throw new ParamInvalidException("Email đăng ký không chính xác");
        }
        user.setPassword(passwordEncoder.encode("Viettravel@123"));
        userRepository.save(user);
    }
}
