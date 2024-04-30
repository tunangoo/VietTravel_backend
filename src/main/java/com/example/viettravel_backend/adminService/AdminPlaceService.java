package com.example.viettravel_backend.adminService;

import com.example.viettravel_backend.dto.request.AddPlaceRequest;
import com.example.viettravel_backend.entity.Place;
import com.example.viettravel_backend.entity.PlaceImage;
import com.example.viettravel_backend.exception.ParamInvalidException;
import com.example.viettravel_backend.repository.PlaceImageRepository;
import com.example.viettravel_backend.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminPlaceService {
    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;

    @Transactional
    public void createNewPlace(AddPlaceRequest request) throws ResponseStatusException {
        if(request == null) {
            throw new ParamInvalidException("Request rỗng");
        }
        if(placeRepository.existsByName(request.getName())) {
            throw new ParamInvalidException("Tên địa điểm đã tồn tại");
        }
        if(request.getPrice() < 0) {
            throw new ParamInvalidException("Giá vé không được < 0");
        }

        Place place = request.mappingToPlace();

        placeRepository.save(place);

        // tạo place image
        Set<PlaceImage> placeImages = new HashSet<>();
        try {
            for (String image : request.getImages()) {
                PlaceImage placeImage = PlaceImage.builder()
                        .place(place)
                        .imageUrl(image)
                        .build();
                placeImages.add(placeImage);
            }
        } catch (Exception e) {
            throw new ParamInvalidException("Tạo địa điểm mới không thành công");
        }

        try {
            placeImageRepository.saveAll(placeImages);
        } catch (Exception e) {
            throw new ParamInvalidException("Tạo địa điểm mới không thành công");
        }
    }
}
