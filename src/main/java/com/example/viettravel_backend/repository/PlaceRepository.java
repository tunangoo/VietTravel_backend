package com.example.viettravel_backend.repository;

import com.example.viettravel_backend.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Boolean existsByName(String name);

}
