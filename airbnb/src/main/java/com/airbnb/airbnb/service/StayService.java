package com.airbnb.airbnb.service;

import com.airbnb.airbnb.dto.StayPatchDto;
import com.airbnb.airbnb.dto.StayPostDto;
import com.airbnb.airbnb.entity.Stay;
import com.airbnb.airbnb.exception.BusinessLogicException;
import com.airbnb.airbnb.exception.ExceptionCode;
import com.airbnb.airbnb.mapper.StayMapper;
import com.airbnb.airbnb.repository.StayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StayService {
    private final StayRepository stayRepository;
    private final StayMapper stayMapper;

    @Transactional
    public void createStay (StayPostDto stayPostDto) {
       stayRepository.save(stayMapper.toStay(stayPostDto));
    }

    @Transactional
    public Stay findVerifiedStay (Long id) {
        return stayRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.STAY_NOT_FOUND));
    }

    @Transactional
    public List<Stay> findStays (int page, int size) {
        Page<Stay> pageStays = stayRepository.findAll(PageRequest.of(page-1, size, Sort.by("id").descending()));
        return pageStays.getContent();
    }

    @Transactional
    public void updateStay (StayPatchDto stayPatchDto, Long id) {
        Stay findStay = findVerifiedStay(id);
        Optional.ofNullable(stayPatchDto.getInfo())
                .ifPresent(info -> findStay.setInfo(info));
        Optional.ofNullable(stayPatchDto.getCountry())
                .ifPresent(country -> findStay.setCountry(country));
        Optional.ofNullable(stayPatchDto.getCharge())
                .ifPresent(charge -> findStay.setCharge(charge));
        Optional.ofNullable(stayPatchDto.getBeds())
                .ifPresent(beds -> findStay.setBeds(beds));
        Optional.ofNullable(stayPatchDto.getBedrooms())
                .ifPresent(bedrooms -> findStay.setBedrooms(bedrooms));
        Optional.ofNullable(stayPatchDto.getBathrooms())
                .ifPresent(bathrooms -> findStay.setBathrooms(bathrooms));

        stayRepository.save(findStay);
    }

    @Transactional
    public void removeStay (Long id) {
        stayRepository.delete(findVerifiedStay(id));
    }

}
