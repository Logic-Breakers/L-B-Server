package com.airbnb.airbnb.repository;

import com.airbnb.airbnb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
