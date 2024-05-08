package com.example.viettravel_backend.controller;

import com.example.viettravel_backend.dto.request.*;
import com.example.viettravel_backend.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/favorite", produces = {"application/json;charset=UTF-8", "text/plain;charset=UTF-8"})
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(
            @RequestBody @Valid AddFavoriteRequest request, BindingResult bindingResult
    ) throws ResponseStatusException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Một hoặc nhiều trường truyền vào không hợp lệ!");
        }
        favoriteService.AddFavorite(request);
        return ResponseEntity.ok("Thêm địa điểm yêu thích thành công");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFavorite(
            @RequestBody @Valid DeleteFavoriteRequest request, BindingResult bindingResult
    ) throws ResponseStatusException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Một hoặc nhiều trường truyền vào không hợp lệ!");
        }
        favoriteService.DeleteFavorite(request);
        return ResponseEntity.ok("Xóa địa điểm yêu thích thành công");
    }
}
