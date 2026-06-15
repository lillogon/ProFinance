package com.lillogon.profinance.mapper;

import com.lillogon.profinance.dto.category.CategoryRequestDTO;
import com.lillogon.profinance.dto.category.CategoryResponseDTO;
import com.lillogon.profinance.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public CategoryResponseDTO toResponse(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getDescription(),
                category.getActive(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                category.getBlockedAt()
        );
    }

    public List<CategoryResponseDTO> toResponseList(List<Category> categories) {
        return categories.stream()
                .map(this::toResponse)
                .toList();
    }

    public Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setDescription(dto.description());
        category.setActive(dto.active() != null ? dto.active() : true);
        return category;
    }
}
