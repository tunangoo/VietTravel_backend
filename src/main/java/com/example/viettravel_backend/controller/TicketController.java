package com.example.viettravel_backend.controller;


import com.example.viettravel_backend.dto.request.CreateTicketRequest;
import com.example.viettravel_backend.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/ticket", produces = {"application/json;charset=UTF-8", "text/plain;charset=UTF-8"})
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTicket() throws ResponseStatusException {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTicket(
            @RequestBody @Valid CreateTicketRequest request, BindingResult bindingResult
    ) throws ResponseStatusException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Một hoặc nhiều trường truyền vào không hợp lệ!");
        }
        ticketService.createTicket(request);
        return ResponseEntity.ok("Tạo mới ticket thành công");
    }
}
