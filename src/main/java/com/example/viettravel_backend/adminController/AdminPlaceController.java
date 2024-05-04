package com.example.viettravel_backend.adminController;


import com.example.viettravel_backend.adminService.AdminPlaceService;
import com.example.viettravel_backend.dto.request.AddPlaceRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/admin/place", produces = "text/plain;charset=UTF-8")
@RequiredArgsConstructor
public class AdminPlaceController {
    private final AdminPlaceService adminPlaceService;

    @PostMapping("/add")
    public ResponseEntity<?> addPlace(
            @RequestBody @Valid AddPlaceRequest request, BindingResult bindingResult
    ) throws ResponseStatusException {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Một hoặc nhiều trường truyền vào không hợp lệ!");
        }
        adminPlaceService.createNewPlace(request);
        return ResponseEntity.ok("Thêm địa điểm mới thành công");
    }
}
