package com.example.viettravel_backend.service;

import com.example.viettravel_backend.dto.response.GetPlaceDetailResponse;
import com.example.viettravel_backend.dto.response.GetPlaceSummaryResponse;
import com.example.viettravel_backend.entity.Place;
import com.example.viettravel_backend.exception.ParamInvalidException;
import com.example.viettravel_backend.repository.FavoriteRepository;
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
    private final FavoriteRepository favoriteRepository;
    private final UserService userService;

    private List<GetPlaceSummaryResponse> convertPlaceSummary(List<Place> places) {
        List<GetPlaceSummaryResponse> responses = new ArrayList<>();
        for (Place place : places) {
            boolean favorite;
            if(favoriteRepository.findByUserIdAndPlaceId(userService.getId(), place.getId()).isPresent()) {
                favorite = true;
            } else {
                favorite = false;
            }
            responses.add(GetPlaceSummaryResponse.convertfromPlace(place, favorite));
        }
        return responses;
    }

    public List<GetPlaceSummaryResponse> getAllPlaces() throws ResponseStatusException {
        List<Place> places = placeRepository.findAll();
        return convertPlaceSummary(places);
    }

    public List<GetPlaceSummaryResponse> getRecommendedPlaces() throws ResponseStatusException {
        List<Place> places = placeRepository.findRecommend();
        return convertPlaceSummary(places);
    }

    public List<GetPlaceSummaryResponse> getFreePlaces() throws ResponseStatusException {
        List<Place> places = placeRepository.findFree();
        return convertPlaceSummary(places);
    }

    public GetPlaceDetailResponse getPlaceDetail(Long place_id) throws ResponseStatusException {
        Place place = placeRepository.findById(place_id)
                .orElseThrow(() -> new ParamInvalidException("Place_id không chính xác"));

        List<String> images = placeImageRepository.findAllByPlaceId(place_id);

        boolean isFavorite;
        if(favoriteRepository.findByUserIdAndPlaceId(userService.getId(), place_id).isPresent()){
            isFavorite = true;
        } else {
            isFavorite = false;
        }

        return GetPlaceDetailResponse.builder()
                .placeId(place.getId())
                .name(place.getName())
                .address(place.getAddress())
                .price(place.getPrice())
                .description(place.getDescription())
                .images(images)
                .favorite(isFavorite)
                .rating(place.getRating())
                .build();
    }
}
