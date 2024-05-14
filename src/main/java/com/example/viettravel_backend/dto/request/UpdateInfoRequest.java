package com.example.viettravel_backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInfoRequest {
    @NotNull
    private String fullName;

//    private LocalDate birthday;

    @Pattern(regexp = "^[0-9]+$")
    private String phoneNumber;

    @NotNull
    private String email;

    private String address;
}
