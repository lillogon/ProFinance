package com.lillogon.profinance.service;

import com.lillogon.profinance.dto.category.CategoryRequestDTO;
import com.lillogon.profinance.dto.category.CategoryResponseDTO;
import com.lillogon.profinance.dto.category.CategoryUpdateDTO;
import com.lillogon.profinance.entity.Category;
import com.lillogon.profinance.exception.BusinessException;
import com.lillogon.profinance.mapper.CategoryMapper;
import com.lillogon.profinance.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    // Register category
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        String description = dto.description().trim();

        if (repository.findByDescription(description).isPresent()) {
            throw new BusinessException("Description already registered.");
        }

        Category entity = mapper.toEntity(dto);

        if (dto.active() != null && !dto.active()) {
            entity.setBlockedAt(OffsetDateTime.now());
        }

        Category saved = repository.save(entity);

        return mapper.toResponse(saved);
    }

    // Get all categories
    public Page<CategoryResponseDTO> getCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> result = repository.findAll(pageable);

        return result.map(mapper::toResponse);
    }

    // Get category by id
    public CategoryResponseDTO getCategoryById(UUID id) {
        return mapper.toResponse(getCategory(id));
    }

    // UPDATE category
    public CategoryResponseDTO putCategory(UUID id, CategoryUpdateDTO dto) {
        Category entity = getCategory(id);

        if (dto.description() != null) {
            String description = dto.description().trim();

            if (!description.equals(entity.getDescription())) {
                if (repository.findByDescription(description).isPresent()) {
                    throw new BusinessException("Description already registered.");
                }

                entity.setDescription(description);
            }
        }

        if (dto.active() != null && !dto.active().equals(entity.getActive())) {
            entity.setActive(dto.active());

            if (!dto.active()) {
                entity.setBlockedAt(OffsetDateTime.now());
            } else {
                entity.setBlockedAt(null);
            }
        }

        Category saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    //Get Category
    private Category getCategory(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Category not found."));
    }
}
