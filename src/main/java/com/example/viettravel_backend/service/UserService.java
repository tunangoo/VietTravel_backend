package com.example.viettravel_backend.service;

import com.example.viettravel_backend.dto.request.UpdateProfileRequest;
import com.example.viettravel_backend.entity.User;
import com.example.viettravel_backend.exception.ParamInvalidException;
import com.example.viettravel_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User updateProfile(UpdateProfileRequest request) throws ResponseStatusException {
        User user = userRepository
                .findById(request.getId())
                .orElseThrow(() -> new ParamInvalidException("Người dùng không tồn tại"));

        // Cập nhật các trường thông tin nếu được cung cấp trong yêu cầu
        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getBirthday() != null) {
            user.setBirthday(request.getBirthday());
        }
        if (request.getAddress() != null) {
            user.setAddress(request.getAddress());
        }
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new ParamInvalidException("Cập nhật thông tin không thành công");
        }
        return user;
    }
}
