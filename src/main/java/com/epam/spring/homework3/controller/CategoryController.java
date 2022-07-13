package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.api.CategoryApi;
import com.epam.spring.homework3.model.DTO.CategoryDTO;
import com.epam.spring.homework3.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;

    @Override
    public List<CategoryDTO> getAllActivityCategories() {
        return categoryService.getAllCategories();
    }

    @Override
    public CategoryDTO getActivityCategory(@PathVariable String name) {
        return categoryService.getCategory(name);
    }

    @Override
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }

    @Override
    public ResponseEntity<Void> removeCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
