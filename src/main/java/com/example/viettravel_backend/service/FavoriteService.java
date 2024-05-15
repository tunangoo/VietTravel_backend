package com.example.viettravel_backend.service;

import com.example.viettravel_backend.dto.request.AddFavoriteRequest;
import com.example.viettravel_backend.dto.request.DeleteFavoriteRequest;
import com.example.viettravel_backend.dto.response.GetPlaceSummaryResponse;
import com.example.viettravel_backend.entity.*;
import com.example.viettravel_backend.repository.*;
import com.example.viettravel_backend.exception.ParamInvalidException;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class FavoriteService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final FavoriteRepository favoriteRepository;
    private final UserService userService;

    public void AddFavorite(AddFavoriteRequest request) throws ResponseStatusException {
        User user = userRepository.findById(userService.getId())
                .orElseThrow(() -> new ParamInvalidException("User không tồn tại"));

        Place place = placeRepository.findById(request.getPlace_id())
                .orElseThrow(() -> new ParamInvalidException("Place không tồn tại"));

        if(favoriteRepository.findByUserIdAndPlaceId(userService.getId(), request.getPlace_id()).isPresent()) {
            throw new ParamInvalidException("Favorite đã tồn tại cho user và place đã cho");
        }

        Favorite favorite = Favorite.builder()
                .user(user)
                .place(place)
                .build();
        favoriteRepository.save(favorite);
    }

    public void DeleteFavorite(DeleteFavoriteRequest request) throws ResponseStatusException {
        if(placeRepository.existsById(request.getPlace_id())) {
            throw new ParamInvalidException("Place không tồn tại");
        }

        var favorite = favoriteRepository.findByUserIdAndPlaceId(userService.getId(), request.getPlace_id());
        if(favorite.isPresent()) {
            favoriteRepository.delete(favorite.get());
        } else {
            throw new ParamInvalidException("Favorite không tồn tại cho user và place đã cho");
        }
    }

    public List<GetPlaceSummaryResponse> getAllFavorite() throws ResponseStatusException {
        List<Favorite> favorites = favoriteRepository.findAllByUserId(userService.getId());

        List<GetPlaceSummaryResponse> responses = new ArrayList<>();
        for(Favorite favorite : favorites) {
            Place place = favorite.getPlace();
            responses.add(GetPlaceSummaryResponse.convertfromPlace(place, true));
        }

        return responses;
    }
}
