package com.example.viettravel_backend.controller;

import com.example.viettravel_backend.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/place", produces = {"application/json;charset=UTF-8", "text/plain;charset=UTF-8"})
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllPlaces() throws ResponseStatusException {
        return ResponseEntity.ok(placeService.getAllPlaces());
    }

    @GetMapping("/detail/{place_id}")
    public ResponseEntity<?> getPlaceDetail(
            @PathVariable Long place_id
    ) throws ResponseStatusException {
        return ResponseEntity.ok(placeService.getPlaceDetail(place_id));
    }
}
