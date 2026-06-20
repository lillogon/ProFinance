package com.lillogon.profinance.controller;

import com.lillogon.profinance.dto.category.CategoryRequestDTO;
import com.lillogon.profinance.dto.category.CategoryResponseDTO;
import com.lillogon.profinance.dto.category.CategoryUpdateDTO;
import com.lillogon.profinance.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCategory(dto));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponseDTO>> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.getCategories(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> putCategory(@PathVariable UUID id, @RequestBody @Valid CategoryUpdateDTO dto) {
        return ResponseEntity.ok(service.putCategory(id, dto));
    }
}
