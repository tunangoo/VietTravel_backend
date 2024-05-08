package com.example.viettravel_backend.controller;

import com.example.viettravel_backend.dto.request.ChangePasswordRequest;
import com.example.viettravel_backend.dto.request.UpdateInfoRequest;
import com.example.viettravel_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/user", produces = {"application/json;charset=UTF-8", "text/plain;charset=UTF-8"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("/update_info")
    public ResponseEntity<?> updateInfo(
            @RequestBody @Valid UpdateInfoRequest request, BindingResult bindingResult
    ) throws ResponseStatusException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Một hoặc nhiều trường truyền vào không hợp lệ!");
        }
        userService.updateInfo(request);
        return ResponseEntity.ok("Cập nhật thông tin cá nhân thành công");
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfo(

    ) throws ResponseStatusException {
        return ResponseEntity.ok(userService.getInfo());
    }

    @PatchMapping("/change_password")
    public ResponseEntity<?> changePassword(
            @RequestBody @Valid ChangePasswordRequest request, BindingResult bindingResult
    ) throws ResponseStatusException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Một hoặc nhiều trường truyền vào không hợp lệ!");
        }
        userService.changePassword(request);
        return ResponseEntity.ok("Thay đổi mật khẩu thành công");
    }
}
