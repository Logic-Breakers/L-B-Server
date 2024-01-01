package com.airbnb.airbnb.controller;

import com.airbnb.airbnb.dto.StayPatchDto;
import com.airbnb.airbnb.dto.StayPostDto;
import com.airbnb.airbnb.entity.Stay;
import com.airbnb.airbnb.mapper.StayMapper;
import com.airbnb.airbnb.service.StayService;
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
    public ResponseEntity patchStay (@Valid @RequestBody StayPatchDto stayPatchDto,
                                     @PathVariable("stay-id") Long id){
//                                     @RequestPart(value = "image") List<MultipartFile> images) {
//        if (images == null) {}
        stayService.updateStay(stayPatchDto,id);
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
        return new ResponseEntity(stayService.findStays(page, size), HttpStatus.OK);
    }
    @Transactional
    @GetMapping("/category/{category-id}")
    public ResponseEntity getStaysByCategory (@Positive @RequestParam int page,
                                              @Positive @RequestParam int size,
                                              @PathVariable("category-id") Long categoryId) {
        return new ResponseEntity(stayService.findStaysByCategory(page, size, categoryId), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/country")
    public ResponseEntity getStaysByCountry (@Positive @RequestParam int page,
                                             @Positive @RequestParam int size,
                                             @RequestParam(required = false, defaultValue = "대한민국") String country) {
        return new ResponseEntity(stayService.findStaysByCountry(page, size, country), HttpStatus.OK);
    }
    @Transactional
    @GetMapping("/filter")
    public ResponseEntity getStaysByFilter (@Positive @RequestParam int page,
                                            @Positive @RequestParam int size,
                                            @RequestParam(required = false, defaultValue = "대한민국") String country,
                                            @RequestParam(required = false) Long beds,
                                            @RequestParam(required = false) Long bathrooms,
                                            @RequestParam(required = false) Stay.PropertyType propertyType) {
        return new ResponseEntity(stayService.findStaysByFilter(page, size, country, beds, bathrooms, propertyType), HttpStatus.OK);
    }
}
