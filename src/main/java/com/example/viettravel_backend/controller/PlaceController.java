package com.example.viettravel_backend.controller;

import com.example.viettravel_backend.dto.request.GetPlaceDetailRequest;
import com.example.viettravel_backend.service.PlaceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/detail")
    public ResponseEntity<?> getPlaceDetail(
            @RequestBody GetPlaceDetailRequest request
    ) throws ResponseStatusException {
        return ResponseEntity.ok(placeService.getPlaceDetail(request));
    }
}
