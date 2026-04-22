package com.lillogon.pro_finance.services;

import com.lillogon.pro_finance.domain.category.Category;
import com.lillogon.pro_finance.domain.category.CategoryRequestDTO;
import com.lillogon.pro_finance.domain.category.CategoryResponseDTO;
import com.lillogon.pro_finance.exceptions.ResourceNotFoundException;
import com.lillogon.pro_finance.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponseDTO createCategory(CategoryRequestDTO data){
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

        return CategoryResponseDTO.from(newCategory);
    }

    public List<CategoryResponseDTO> getCategories(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable)
                .getContent().stream()
                .map(CategoryResponseDTO::from)
                .toList();
    }

    public CategoryResponseDTO getCategoryById(UUID id){
        return categoryRepository.findById(id)
                .map(CategoryResponseDTO::from)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found."));
    }

    public void deleteCategory(UUID id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found."));

        categoryRepository.delete(category);
    }

    public CategoryResponseDTO updateCategory(UUID id, CategoryRequestDTO data){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found."));

        if (data.description() != null){
            if (data.description().isBlank()){
                throw new IllegalArgumentException("Description cannot be empty.");
            }
            category.setDescription(data.description().toLowerCase().trim());
        }

        LocalDateTime now = LocalDateTime.now();

        if (data.active() != null){
            category.setActive(data.active());
            if (data.active()){
                category.setBlockedAt(null);
            } else {
                category.setBlockedAt(now);
            }
        }

        category.setUpdatedAt(now);

        return CategoryResponseDTO.from(categoryRepository.save(category));
    }
}