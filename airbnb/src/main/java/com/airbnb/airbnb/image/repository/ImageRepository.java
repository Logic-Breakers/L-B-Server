package com.airbnb.airbnb.image.repository;

import com.airbnb.airbnb.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
