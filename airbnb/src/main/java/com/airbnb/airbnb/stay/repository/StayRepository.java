package com.airbnb.airbnb.stay.repository;

import com.airbnb.airbnb.stay.entity.Stay;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface StayRepository extends JpaRepository<Stay,Long>, JpaSpecificationExecutor<Stay> {

    List<Stay> findAllByCountry(String country, Pageable pageable);

    List<Stay> findAllByCategory(String categoryName, Pageable pageable);
}
