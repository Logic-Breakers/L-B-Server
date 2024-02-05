package com.airbnb.airbnb.stay.repository;

import com.airbnb.airbnb.stay.entity.StayCategories;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StayCategoriesRepository extends JpaRepository<StayCategories, Long> {

    List<StayCategories> findAllByCategory_CategoryName(String categoryName, Pageable pageable);
    void deleteAllByStay_Id(Long stayId);
}
