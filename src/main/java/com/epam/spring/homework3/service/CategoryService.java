package com.epam.spring.homework3.service;

import com.epam.spring.homework3.model.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategory(String name);

    void deleteCategory(Long id);
}
