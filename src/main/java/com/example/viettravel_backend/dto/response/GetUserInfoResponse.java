package com.example.viettravel_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserInfoResponse {
    private String fullName;

    private LocalDate birthday;

    private String phoneNumber;

    private String email;

    private String address;

    private String avatar;
}
