package com.lillogon.pro_finance.controllers;

import com.lillogon.pro_finance.domain.category.CategoryRequestDTO;
import com.lillogon.pro_finance.domain.category.CategoryResponseDTO;
import com.lillogon.pro_finance.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryRequestDTO body){
        return ResponseEntity.ok(categoryService.createCategory(body));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(categoryService.getCategories(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable UUID id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable UUID id, @RequestBody CategoryRequestDTO body){
        return ResponseEntity.ok(categoryService.updateCategory(id, body));
    }
}