package com.example.viettravel_backend.repository;

import com.example.viettravel_backend.entity.Favorite;
import com.example.viettravel_backend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(
            value = "select ticket_id " +
                    "from ticket_users " +
                    "where user_id = :user_id " +
                    "order by ticket_id desc",
            nativeQuery = true
    )
    List<Long> findByUserIdGetTicket(
            @Param("user_id") Long user_id
    );

    @Query(
            value = "select place_id " +
                    "from ticket_places " +
                    "where ticket_id = :ticket_id",
            nativeQuery = true
    )
    Long findByTicketIdGetPlace(
            @Param("ticket_id") Long ticket_id
    );
}
