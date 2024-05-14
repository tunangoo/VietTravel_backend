package com.example.viettravel_backend.repository;

import com.example.viettravel_backend.entity.Favorite;
import com.example.viettravel_backend.entity.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceImageRepository  extends JpaRepository<PlaceImage, Long> {
    @Query(
            value = "select image_url " +
                    "from place_images " +
                    "where place_id = :place_id",
            nativeQuery = true
    )
    List<String> findAllByPlaceId(
            @Param("place_id") Long place_id
    );
}
