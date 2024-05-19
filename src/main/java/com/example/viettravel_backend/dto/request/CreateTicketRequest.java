package com.example.viettravel_backend.dto.request;

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
public class CreateTicketRequest {
    @NotNull
    private Long place_id;

    @NotNull
    private LocalDate entryTime;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer totalAmount;
}
