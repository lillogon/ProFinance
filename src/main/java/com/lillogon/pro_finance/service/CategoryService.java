package com.lillogon.pro_finance.service;

import com.lillogon.pro_finance.domain.category.Category;
import com.lillogon.pro_finance.domain.category.CategoryRequestDTO;
import com.lillogon.pro_finance.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DecimalStyle;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category createCategory(CategoryRequestDTO data){
        Category newCategory = new Category();
        LocalDateTime now = LocalDateTime.now();

        if (data.description() == null || data.description().isBlank()){
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }

        boolean active = data.active() != null ? data.active() : true;

        newCategory.setDescription(data.description().toLowerCase().trim());
        newCategory.setActive(active);
        newCategory.setCreatedAt(now);
        newCategory.setUpdatedAt(now);
        if (!active){
            newCategory.setBlockedAt(now);
        }

        repository.save(newCategory);

        return newCategory;
    }
}