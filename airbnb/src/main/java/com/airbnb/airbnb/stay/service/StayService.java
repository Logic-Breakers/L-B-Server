package com.airbnb.airbnb.stay.service;

import com.airbnb.airbnb.member.entity.Member;
import com.airbnb.airbnb.member.service.MemberService;
import com.airbnb.airbnb.stay.specification.StaySpecification;
import com.airbnb.airbnb.image.service.ImageService;
import com.airbnb.airbnb.stay.dto.StayPatchDto;
import com.airbnb.airbnb.stay.dto.StayPostDto;
import com.airbnb.airbnb.stay.entity.Category;
import com.airbnb.airbnb.stay.entity.Stay;
import com.airbnb.airbnb.stay.entity.StayCategories;
import com.airbnb.airbnb.exception.BusinessLogicException;
import com.airbnb.airbnb.exception.ExceptionCode;
import com.airbnb.airbnb.stay.mapper.StayMapper;
import com.airbnb.airbnb.stay.repository.StayCategoriesRepository;
import com.airbnb.airbnb.stay.repository.StayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class StayService {
    private final StayRepository stayRepository;
    private final StayMapper stayMapper;
    private final CategoryService categoryService;
    private final StayCategoriesRepository stayCategoriesRepository;
    private final ImageService imageService;
    private final MemberService memberService;


    @Transactional
    public void createStay(StayPostDto stayPostDto, String categoryName, List<MultipartFile> images, Member host) {
//    카테고리 이름으로 등록하게 수정, 일단 카테고리 하나만 되게 수정
//    public void createStay(StayPostDto stayPostDto, Set<Long> categoryIds, List<MultipartFile> images) {
        if (stayPostDto.getStar() == null) {
            stayPostDto.setStar(0.0);
        }
        Stay stay = stayMapper.toStay(stayPostDto);
        stay.setCreatedAt(LocalDateTime.now());
        stay.setMember(host);

        //카테고리 이름으로 등록하게 수정, 일단 카테고리 하나만 되게 수정
        List<StayCategories> setStayCategories = new ArrayList<>();
        Category category = categoryService.findVerifiedCategoryByName(categoryName);
        StayCategories stayCategories = new StayCategories(stay, category);
        setStayCategories.add(stayCategories);
        stayCategoriesRepository.save(stayCategories);
        stay.setStayCategories(setStayCategories);

//        //category 설정
//        List<StayCategories> setStayCategories = new ArrayList<>();
//        for (Long categoryId : categoryIds) {
//            Category category = categoryService.findVerifiedCategory(categoryId);
//            StayCategories stayCategories = new StayCategories(stay, category);
//            setStayCategories.add(stayCategories);
//            stayCategoriesRepository.save(stayCategories);
//        }
//        stay.setStayCategories(setStayCategories);

        //이미지 업로드
        stay.setImages(imageService.uploadImage(images, stay));
        stayRepository.save(stay);
    }

    @Transactional(readOnly = true)
    public Stay findVerifiedStay (Long id) {
        return stayRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.STAY_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<Stay> findStays (int page, int size) {
        Page<Stay> pageStays = stayRepository.findAll(PageRequest.of(page-1, size, Sort.by("id").descending()));
        return pageStays.getContent();
    }

    @Transactional
    public void updateStay (StayPatchDto stayPatchDto, Long id) {
        Stay findStay = findVerifiedStay(id);
        Optional.ofNullable(stayPatchDto.getHouseName())
                .ifPresent(houseName -> findStay.setHouseName(houseName));
        Optional.ofNullable(stayPatchDto.getInfo())
                .ifPresent(info -> findStay.setInfo(info));
        Optional.ofNullable(stayPatchDto.getCountry())
                .ifPresent(country -> findStay.setCountry(country));
        Optional.ofNullable(stayPatchDto.getAddress())
                .ifPresent(address -> findStay.setAddress(address));
//        Optional.ofNullable(stayPatchDto.isGuestFavourite())
//                .ifPresent(guestFavourite -> findStay.setGuestFavourite(guestFavourite));
        Optional.ofNullable(stayPatchDto.getGuest())
                .ifPresent(guest -> findStay.setGuest(guest));
        Optional.ofNullable(stayPatchDto.getStar())
                .ifPresent(star -> findStay.setStar(star));
        Optional.ofNullable(stayPatchDto.getPlaceType())
                .ifPresent(placeType -> findStay.setPlaceType(placeType));
        Optional.ofNullable(stayPatchDto.getPropertyType())
                .ifPresent(propertyType -> findStay.setPropertyType(propertyType));
        Optional.ofNullable(stayPatchDto.getPrice())
                .ifPresent(price -> findStay.setPrice(price));
        Optional.ofNullable(stayPatchDto.getBeds())
                .ifPresent(beds -> findStay.setBeds(beds));
        Optional.ofNullable(stayPatchDto.getBedrooms())
                .ifPresent(bedrooms -> findStay.setBedrooms(bedrooms));
        Optional.ofNullable(stayPatchDto.getBathrooms())
                .ifPresent(bathrooms -> findStay.setBathrooms(bathrooms));
        Optional.ofNullable(stayPatchDto.getCategories())
                .ifPresent(categories -> {
                        stayCategoriesRepository.deleteAllByStay_Id(id);
                        for (Long category : categories) {
                        StayCategories stayCategories = new StayCategories(findStay, categoryService.findVerifiedCategory(category));
                        stayCategoriesRepository.save(stayCategories);
                        }});
        stayRepository.save(findStay);
    }

    @Transactional
    public void removeStay (Long id) {
        stayRepository.delete(findVerifiedStay(id));
    }

    @Transactional(readOnly = true)
    public List<Stay> findStaysByCategory (int page, int size, String categoryName) {
        List<StayCategories> staysByCategory = stayCategoriesRepository.findAllByCategory_CategoryName(categoryName,PageRequest.of(page-1, size, Sort.by("id").descending()));
        List<Stay> stays = new ArrayList<>();
        for (StayCategories stayCategories : staysByCategory) {
            Stay stay = stayCategories.getStay();
            stays.add(stay);
        }
        return stays;
    }

    @Transactional(readOnly = true)
    public List<Stay> findStaysByCountry (int page, int size, String country) {
        return stayRepository.findAllByCountry(country,PageRequest.of(page-1, size, Sort.by("id").descending()
        ));
    }
    @Transactional(readOnly = true)
    public List<Stay> findStaysByFilter(int page, int size, String country, Stay.PlaceType placeType, Long minPrice, Long maxPrice, Long beds, Long bathrooms, Stay.PropertyType propertyType) {
        Specification<Stay> specification = (root, query, criteriaBuilder) -> null;

        if (country != null) {
            specification = specification.and(StaySpecification.equalCountry(country));
        }

        if (placeType != null) {
            specification = specification.and(StaySpecification.equalPlaceType(placeType));
        }

        if (minPrice != null && maxPrice != null) {
            specification = specification.and(StaySpecification.filterByPriceRange(minPrice, maxPrice));
        } else if (minPrice != null && maxPrice == null) {
            specification = specification.and(StaySpecification.filterByPriceRange(minPrice,9999999999L));
        }
        else if (minPrice == null && maxPrice != null) {
            specification = specification.and(StaySpecification.filterByPriceRange(0L, maxPrice));
        }
        else {specification = specification.and(StaySpecification.filterByPriceRange(0L,9999999999L));}

//        if (guestFavourite != null) {
//            specification = specification.and(StaySpecification.equalGuestFavourite(guestFavourite));
//        }

        if (beds != null) {
            specification = specification.and(StaySpecification.equalBeds(beds));
        }

        if (bathrooms != null) {
            specification = specification.and(StaySpecification.equalBathrooms(bathrooms));
        }

        if (propertyType != null) {
            specification = specification.and(StaySpecification.equalPropertyType(propertyType));
        }

        return stayRepository.findAll(specification, PageRequest.of(page-1, size, Sort.by("id").descending())).getContent();
    }

}
