package com.example.viettravel_backend.repository;

import com.example.viettravel_backend.entity.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceImageRepository  extends JpaRepository<PlaceImage, Long> {

}
