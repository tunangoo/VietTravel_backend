package com.example.viettravel_backend.service;

import com.example.viettravel_backend.dto.request.ChangePasswordRequest;
import com.example.viettravel_backend.dto.request.UpdateInfoRequest;
import com.example.viettravel_backend.dto.response.GetUserInfoResponse;
import com.example.viettravel_backend.entity.User;
import com.example.viettravel_backend.exception.ParamInvalidException;
import com.example.viettravel_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private ModelMapper modelMapper;
    private final UserRepository userRepository;

    public User getUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ParamInvalidException("User không tồn tại"));
    }

    public Long getId() {
        return getUser().getId();
    }

    public GetUserInfoResponse getInfo() {
        User user = this.getUser();
        return this.modelMapper.map(user, GetUserInfoResponse.class);
    }

    public void updateInfo(UpdateInfoRequest request) throws ResponseStatusException {
        User user = userRepository
                .findById(getId())
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
    }
}
