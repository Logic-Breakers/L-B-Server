package com.airbnb.airbnb.stay.controller;

import com.airbnb.airbnb.stay.dto.StayPatchDto;
import com.airbnb.airbnb.stay.dto.StayPostDto;
import com.airbnb.airbnb.stay.entity.Stay;
import com.airbnb.airbnb.stay.mapper.StayMapper;
import com.airbnb.airbnb.image.service.ImageService;
import com.airbnb.airbnb.stay.service.StayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/stays")
@RequiredArgsConstructor
public class StayController {


    private final StayMapper stayMapper;
    private final StayService stayService;
    private final ImageService imageService;

    @Transactional
    @PostMapping
    public ResponseEntity postStay (@Valid @RequestPart(value = "stay") StayPostDto stayPostDto,
                                    @RequestParam Set<Long> categoryIds,
                                    @RequestPart(value = "image") List<MultipartFile> images) {
        stayService.createStay(stayPostDto, categoryIds, images);
        return new ResponseEntity("숙소 등록이 완료되었습니다.", HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/{stay-id}")
    public ResponseEntity getStay (@PathVariable("stay-id") Long id) {
        return new ResponseEntity(stayMapper.toStayResponseDto(stayService.findVerifiedStay(id)),HttpStatus.OK);
    }

    @Transactional
    @PatchMapping("/{stay-id}")
    public ResponseEntity patchStay (@Valid @RequestPart(value = "stay") StayPatchDto stayPatchDto,
                                     @PathVariable("stay-id") Long id,
                                     @RequestPart(value = "image", required = false) List<MultipartFile> images) {

        stayService.updateStay(stayPatchDto,id);

        //patch시 이미지 첨부 안 했을 때는 있던 그대로, 첨부한 경우엔 추가 업로드 (DB에는 이전에 업로드한 이미지 + 새로 업로드한 이미지)
        if (images != null && !images.isEmpty()) {
            imageService.uploadImage(images,stayService.findVerifiedStay(id));
        }
        return new ResponseEntity("숙소 정보 수정이 완료되었습니다.", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{stay-id}")
    public ResponseEntity deleteStay (@PathVariable("stay-id") Long id) {
        stayService.removeStay(id);
        return new ResponseEntity("숙소 정보가 삭제되었습니다.", HttpStatus.OK);
    }

    @Transactional
    @GetMapping
    public ResponseEntity getStays (@Positive @RequestParam int page,
                                    @Positive @RequestParam int size) {
        return new ResponseEntity(stayMapper.toStaySumResponseDtos(stayService.findStays(page, size)), HttpStatus.OK);
    }
    @Transactional
    @GetMapping("/category")
    public ResponseEntity getStaysByCategory (@Positive @RequestParam int page,
                                              @Positive @RequestParam int size,
                                              @RequestParam String categoryName) {
        return new ResponseEntity(stayMapper.toStayResponseDtos(stayService.findStaysByCategory(page, size, categoryName)), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/country")
    public ResponseEntity getStaysByCountry (@Positive @RequestParam int page,
                                             @Positive @RequestParam int size,
                                             @RequestParam(required = false, defaultValue = "대한민국") String country) {
        return new ResponseEntity(stayMapper.toStayResponseDtos(stayService.findStaysByCountry(page, size, country)), HttpStatus.OK);
    }
    @Transactional
    @GetMapping("/filter")
    public ResponseEntity getStaysByFilter (@Positive @RequestParam int page,
                                            @Positive @RequestParam int size,
                                            @RequestParam(required = false, defaultValue = "대한민국") String country,
                                            @RequestParam(required = false) Stay.PlaceType placeType,
                                            @RequestParam(required = false) Long minPrice,
                                            @RequestParam(required = false) Long maxPrice,
//                                            @RequestParam(required = false) Boolean guestFavourite,
                                            @RequestParam(required = false) Long beds,
                                            @RequestParam(required = false) Long bathrooms,
                                            @RequestParam(required = false) Stay.PropertyType propertyType) {
        return new ResponseEntity(stayMapper.toStayResponseDtos(stayService.findStaysByFilter(page, size, country, placeType, minPrice, maxPrice, beds, bathrooms, propertyType)), HttpStatus.OK);
    }
}
