package com.airbnb.airbnb.repository;

import com.airbnb.airbnb.entity.Stay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StayRepository extends JpaRepository<Stay,Long> {

}
