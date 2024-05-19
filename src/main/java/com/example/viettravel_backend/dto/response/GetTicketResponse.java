package com.example.viettravel_backend.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTicketResponse {
    private Long place_id;

    private String place_image;

    private String place_name;

    private LocalDate entryTime;

    private Integer quantity;

    private Integer totalAmount;
}
