package com.example.viettravel_backend.service;

import com.example.viettravel_backend.dto.request.GetPlaceDetailRequest;
import com.example.viettravel_backend.dto.response.GetAllPlacesResponse;
import com.example.viettravel_backend.dto.response.GetPlaceDetailResponse;
import com.example.viettravel_backend.entity.Place;
import com.example.viettravel_backend.exception.ParamInvalidException;
import com.example.viettravel_backend.repository.PlaceImageRepository;
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
    private final PlaceImageRepository placeImageRepository;

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

    public GetPlaceDetailResponse getPlaceDetail(Long place_id) throws ResponseStatusException {
        Place place = placeRepository.findById(place_id)
                .orElseThrow(() -> new ParamInvalidException("Place_id không chính xác"));

        List<String> images = placeImageRepository.findAllByPlaceId(place_id);

        return GetPlaceDetailResponse.builder()
                .placeId(place.getId())
                .name(place.getName())
                .address(place.getAddress())
                .price(place.getPrice())
                .description(place.getDescription())
                .images(images)
                .build();
    }
}
