package com.airbnb.airbnb.service;

import com.airbnb.airbnb.entity.Category;
import com.airbnb.airbnb.exception.BusinessLogicException;
import com.airbnb.airbnb.exception.ExceptionCode;
import com.airbnb.airbnb.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Category findVerifiedCategory (Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));
    }
}
