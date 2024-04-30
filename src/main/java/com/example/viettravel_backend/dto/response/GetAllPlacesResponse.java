package com.example.viettravel_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllPlacesResponse {
    private Long id;

    private String name;

    private String address;

    private String imageUrl;
}
