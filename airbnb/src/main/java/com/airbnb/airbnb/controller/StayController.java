package com.airbnb.airbnb.controller;

import com.airbnb.airbnb.dto.StayPatchDto;
import com.airbnb.airbnb.dto.StayPostDto;
import com.airbnb.airbnb.entity.Stay;
import com.airbnb.airbnb.mapper.StayMapper;
import com.airbnb.airbnb.service.StayService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/stays")
@RequiredArgsConstructor
public class StayController {


    private final StayMapper stayMapper;
    private final StayService stayService;

    @Transactional
    @PostMapping
    public ResponseEntity postStay (@Valid @RequestBody StayPostDto stayPostDto) {
        stayService.createStay(stayPostDto);
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
                                    @PathVariable("stay-id") Long id) {
        stayService.updateStay(stayPatchDto,id);
        return new ResponseEntity("숙소 정보 수정이 완료되었습니다.", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{stay-id}")
    public ResponseEntity deleteStay (@PathVariable("stay-id") Long id) {
        stayService.removeStay(id);
        return new ResponseEntity("숙소 정보가 삭제되었습니다.", HttpStatus.NO_CONTENT);
    }

    @Transactional
    @GetMapping
    public ResponseEntity getStays (@Positive @RequestParam int page,
                                    @Positive @RequestParam int size) {
        return new ResponseEntity(stayService.findStays(page, size), HttpStatus.OK);
    }
}
