package com.example.viettravel_backend.auth;

import com.example.viettravel_backend.exception.ParamInvalidException;
import com.example.viettravel_backend.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @Autowired
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody @Valid RegisterRequest request, BindingResult bindingResult
    ) throws ResponseStatusException {
        if (bindingResult.hasErrors()) {
            throw new ParamInvalidException("Một hoặc nhiều trường truyền vào không hợp lệ!");
        }
        if(userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Tên người dùng đã tồn tại");
        }
        authenticationService.register(request);
        return ResponseEntity.ok("Đăng ký thành công");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationResquest request
    ) throws ResponseStatusException {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
