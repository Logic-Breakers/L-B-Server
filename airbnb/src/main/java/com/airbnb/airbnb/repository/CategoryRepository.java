package com.airbnb.airbnb.repository;

import com.airbnb.airbnb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findAllByIdIn(Iterable<Long> categoryIds);
}
