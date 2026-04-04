package com.lillogon.pro_finance.services;

import com.lillogon.pro_finance.domain.category.Category;
import com.lillogon.pro_finance.domain.category.CategoryRequestDTO;
import com.lillogon.pro_finance.domain.category.CategoryResponseDTO;
import com.lillogon.pro_finance.exceptions.ResourceNotFoundException;
import com.lillogon.pro_finance.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

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

        categoryRepository.save(newCategory);

        return newCategory;
    }

    public List<CategoryResponseDTO> getCategories(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoriesPage = this.categoryRepository.findAll(pageable);
        return categoriesPage.map(category -> new CategoryResponseDTO(category.getId(), category.getDescription(), category.getActive(), category.getCreatedAt(), category.getBlockedAt(), category.getUpdatedAt()))
                .stream().toList();
    }

    public CategoryResponseDTO getCategoryById(UUID id){
        return categoryRepository.findById(id)
                .map(category -> new CategoryResponseDTO(category.getId(), category.getDescription(), category.getActive(), category.getCreatedAt(), category.getBlockedAt(), category.getUpdatedAt()))
                .orElseThrow(() -> new ResourceNotFoundException("Category not found."));
    }

    public void deleteCategory(UUID id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found."));

        categoryRepository.delete(category);
    }
}