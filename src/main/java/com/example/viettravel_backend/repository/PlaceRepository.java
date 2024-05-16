package com.example.viettravel_backend.repository;

import com.example.viettravel_backend.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Boolean existsByName(String name);

    @Query(
            value = "select * " +
                    "from places " +
                    "order by rating desc " +
                    "limit 10",
            nativeQuery = true
    )
    List<Place> findRecommend();

}
