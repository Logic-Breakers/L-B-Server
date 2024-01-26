package com.airbnb.airbnb.repository;

import com.airbnb.airbnb.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
