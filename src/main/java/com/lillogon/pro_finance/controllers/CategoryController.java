package com.lillogon.pro_finance.controllers;

import com.lillogon.pro_finance.domain.category.Category;
import com.lillogon.pro_finance.domain.category.CategoryRequestDTO;
import com.lillogon.pro_finance.domain.category.CategoryResponseDTO;
import com.lillogon.pro_finance.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryRequestDTO body){
        Category newCategory = this.categoryService.createCategory(body);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<CategoryResponseDTO> allCategories = this.categoryService.getCategories(page, size);
        return ResponseEntity.ok(allCategories);
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
