package com.example.viettravel_backend.controller;

import com.example.viettravel_backend.dto.request.UpdateInfoRequest;
import com.example.viettravel_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
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
}
