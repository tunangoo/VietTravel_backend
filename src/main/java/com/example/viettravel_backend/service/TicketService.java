package com.example.viettravel_backend.service;


import com.example.viettravel_backend.dto.request.CreateTicketRequest;
import com.example.viettravel_backend.dto.response.GetTicketResponse;
import com.example.viettravel_backend.entity.Place;
import com.example.viettravel_backend.entity.Ticket;
import com.example.viettravel_backend.entity.User;
import com.example.viettravel_backend.exception.ParamInvalidException;
import com.example.viettravel_backend.repository.PlaceRepository;
import com.example.viettravel_backend.repository.TicketRepository;
import com.example.viettravel_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    public List<GetTicketResponse> getAllTickets() throws ResponseStatusException {
        List<Long> ticket_ids = ticketRepository.findByUserIdGetTicket(userService.getId());

        List<GetTicketResponse> responses = new ArrayList<>();

        for(Long ticket_id : ticket_ids) {
            Long place_id = ticketRepository.findByTicketIdGetPlace(ticket_id);
            Ticket ticket = ticketRepository.findById(ticket_id).get();
            Place place = placeRepository.findById(place_id)
                    .orElseThrow(() -> new ParamInvalidException("Có lỗi xảy ra"));
            GetTicketResponse response = GetTicketResponse.builder()
                    .place_id(place_id)
                    .place_image(place.getImageUrl())
                    .place_name(place.getName())
                    .entryTime(ticket.getEntryTime())
                    .quantity(ticket.getQuantity())
                    .totalAmount(ticket.getTotalAmount())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    public void createTicket(CreateTicketRequest request) throws ResponseStatusException {
        Set<User> users = new HashSet<>();
        users.add(userService.getUser());

        Set<Place> places = new HashSet<>();
        places.add(placeRepository.findById(request.getPlace_id())
                .orElseThrow(() -> new ParamInvalidException("Place id không tồn tại")));

        if(userService.getUser().getBalance() < request.getTotalAmount()) {
            throw new ParamInvalidException("Số dư trong ví không đủ");
        }

        Ticket ticket = Ticket.builder()
                .users(users)
                .places(places)
                .entryTime(request.getEntryTime())
                .quantity(request.getQuantity())
                .totalAmount(request.getTotalAmount())
                .build();
        try {
            ticketRepository.save(ticket);
            userService.getUser().setBalance(userService.getUser().getBalance() - ticket.getTotalAmount());
            userRepository.save(userService.getUser());
        } catch (Exception e) {
            throw new ParamInvalidException("Tạo ticket không thành công");
        }
    }

}
