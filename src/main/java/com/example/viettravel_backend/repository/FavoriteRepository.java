package com.example.viettravel_backend.repository;

import com.example.viettravel_backend.entity.Favorite;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query(
            value = "select * " +
                    "from favorites " +
                    "where user_id = :user_id and place_id = :place_id " +
                    "limit 1",
            nativeQuery = true
    )
    Optional<Favorite> findByUserIdAndPlaceId(
        @Param("user_id") Long user_id,
        @Param("place_id") Long place_id
    );
}
