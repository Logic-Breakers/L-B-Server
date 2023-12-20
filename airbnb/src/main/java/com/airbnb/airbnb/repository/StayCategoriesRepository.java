package com.airbnb.airbnb.repository;

import com.airbnb.airbnb.entity.StayCategories;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StayCategoriesRepository extends JpaRepository<StayCategories, Long> {

    List<StayCategories> findAllByCategory_Id(Long categoryId, Pageable pageable);
    void deleteAllByStay_Id(Long stayId);
}
