package com.airbnb.airbnb.repository;

import com.airbnb.airbnb.entity.Stay;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StayRepository extends JpaRepository<Stay,Long> {

    List<Stay> findAllByCategoriesId(Long categoryId, Pageable pageable);

    List<Stay> findAllByCountry(String country, Pageable pageable);
}
