package com.example.viettravel_backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteFavoriteRequest {
    @NotNull
    private Long user_id;

    @NotNull
    private Long place_id;
}
