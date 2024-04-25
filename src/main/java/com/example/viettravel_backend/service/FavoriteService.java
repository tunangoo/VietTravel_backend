package com.example.viettravel_backend.service;

import com.example.viettravel_backend.dto.request.AddFavoriteRequest;
import com.example.viettravel_backend.entity.*;
import com.example.viettravel_backend.repository.*;
import com.example.viettravel_backend.exception.ParamInvalidException;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class FavoriteService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final FavoriteRepository favoriteRepository;

    public void AddFavorite(AddFavoriteRequest request) throws ResponseStatusException {
        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(() -> new ParamInvalidException("User không tồn tại"));

        Place place = placeRepository.findById(request.getPlace_id())
                .orElseThrow(() -> new ParamInvalidException("Place không tồn tại"));

        Favorite favorite = Favorite.builder()
                .user(user)
                .place(place)
                .build();
        favoriteRepository.save(favorite);
    }
}
