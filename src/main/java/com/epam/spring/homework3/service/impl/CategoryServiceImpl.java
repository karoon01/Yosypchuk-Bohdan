package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.model.DTO.CategoryDTO;
import com.epam.spring.homework3.exception.EntityNotFoundException;
import com.epam.spring.homework3.model.entity.Category;
import com.epam.spring.homework3.repository.CategoryRepository;
import com.epam.spring.homework3.service.CategoryService;
import com.epam.spring.homework3.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        log.info("Create category: {}", categoryDTO);
        Category category = CategoryMapper.INSTANCE.mapCategory(categoryDTO);
        categoryRepository.save(category);

        return CategoryMapper.INSTANCE.mapCategoryDto(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        log.info("Get all categories");
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper.INSTANCE::mapCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategory(String name) {
        log.info("Get category by name: {}", name);
        Category category = categoryRepository.findCategoryByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Category doesn't exist"));

        return CategoryMapper.INSTANCE.mapCategoryDto(category);
    }

    @Transactional
    @Override
    public void deleteCategory(Long id) {
        log.info("Delete category with id: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category doesn't exist"));
        categoryRepository.delete(category);
    }
}
