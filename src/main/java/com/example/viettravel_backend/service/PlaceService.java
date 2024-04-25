package com.example.viettravel_backend.service;

import com.example.viettravel_backend.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PlaceService {
    public ArrayList<Place> getAllPlaces() throws ResponseStatusException {

    }
}
