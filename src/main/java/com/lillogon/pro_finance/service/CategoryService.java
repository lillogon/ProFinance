package com.lillogon.pro_finance.service;

import com.lillogon.pro_finance.domain.category.Category;
import com.lillogon.pro_finance.domain.category.CategoryRequestDTO;
import com.lillogon.pro_finance.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category createCategory(CategoryRequestDTO data){
        Category newCategory = new Category();
        newCategory.setDescription(data.description());
        newCategory.setActive(data.active());
        newCategory.setCreatedAt(LocalDateTime.now());
        newCategory.setUpdatedAt(LocalDateTime.now());

        repository.save(newCategory);

        return newCategory;
    }
}