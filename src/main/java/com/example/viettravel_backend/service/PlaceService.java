package com.example.viettravel_backend.service;

import com.example.viettravel_backend.dto.response.GetAllPlacesResponse;
import com.example.viettravel_backend.entity.Place;
import com.example.viettravel_backend.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public List<GetAllPlacesResponse> getAllPlaces() throws ResponseStatusException {
        List<GetAllPlacesResponse> responses = new ArrayList<>();

        List<Place> places = placeRepository.findAll();
        for (Place place : places) {
            GetAllPlacesResponse response = GetAllPlacesResponse.builder()
                    .id(place.getId())
                    .name(place.getName())
                    .address(place.getAddress())
                    .imageUrl(place.getImageUrl())
                    .build();
            responses.add(response);
        }

        return responses;
    }
}
