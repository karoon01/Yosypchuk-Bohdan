package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.model.DTO.CategoryDTO;
import com.epam.spring.homework3.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/activity/category/all")
    public List<CategoryDTO> getAllActivityCategories() {
        return categoryService.getAllCategories();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/activity/category/{name}")
    public CategoryDTO getActivityCategory(@PathVariable String name) {
        return categoryService.getCategory(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/activity/category")
    public CategoryDTO getAllActivityCategories(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }

    @DeleteMapping("/admin/activity/category/{id}")
    public ResponseEntity<Void> removeCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
