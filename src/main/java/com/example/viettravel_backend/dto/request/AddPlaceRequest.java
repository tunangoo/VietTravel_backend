package com.example.viettravel_backend.dto.request;

import com.example.viettravel_backend.entity.Place;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddPlaceRequest {
    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String description;

    @NotNull
    private Long price;

    @NotNull
    private String imageUrl;

    @NotNull
    private ArrayList<String> images;

    public Place mappingToPlace() {
        Place place = new Place();
        place.setName(name);
        place.setAddress(address);
        place.setDescription(description);
        place.setPrice(price);
        place.setImageUrl(imageUrl);
        return place;
    }
}
