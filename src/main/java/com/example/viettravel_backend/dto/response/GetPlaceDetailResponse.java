package com.example.viettravel_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPlaceDetailResponse {
    private Long placeId;

    private String name;

    private String address;

    private Long price;

    private String description;

    private List<String> images;
}