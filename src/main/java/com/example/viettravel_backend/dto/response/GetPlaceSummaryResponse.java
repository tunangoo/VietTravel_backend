package com.example.viettravel_backend.dto.response;

import com.example.viettravel_backend.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPlaceSummaryResponse {
    private Long placeId;

    private String name;

    private String address;

    private Long price;

    private String imageUrl;

    private boolean favorite;

    private Double rating;

    public static GetPlaceSummaryResponse convertfromPlace(Place place, boolean favorite) {
        //xử lí address lấy tỉnh ở cuối sau dấy phẩy
        List<String> adr = Arrays.asList(place.getAddress().split(","));
        String address2 = adr.get(adr.size() - 1).strip();
        if(address2.toLowerCase().startsWith("tỉnh ")) {
            address2 = address2.substring(5);
        }
        if(address2.toLowerCase().startsWith("thành phố ")) {
            address2 = address2.substring(10);
        }

        if(address2.toLowerCase().startsWith("tp ")) {
            address2 = address2.substring(3);
        }

        return GetPlaceSummaryResponse.builder()
                .placeId(place.getId())
                .name(place.getName())
                .address(address2)
                .price(place.getPrice())
                .imageUrl(place.getImageUrl())
                .favorite(favorite)
                .rating(place.getRating())
                .build();
    }
}