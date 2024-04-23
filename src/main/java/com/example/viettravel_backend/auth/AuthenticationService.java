package com.example.viettravel_backend.auth;

import com.example.viettravel_backend.config.JwtService;
import com.example.viettravel_backend.entity.Favorite;
import com.example.viettravel_backend.entity.enums.Role;
import com.example.viettravel_backend.entity.User;
import com.example.viettravel_backend.exception.ParamInvalidException;
import com.example.viettravel_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        Favorite cart = new Favorite();
        cart.setUser(user);
    }

    public AuthenticationRespone authenticate(AuthenticationResquest request) {
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
        return AuthenticationRespone.builder()
                .accessToken(accessToken)
                .build();
    }
}
