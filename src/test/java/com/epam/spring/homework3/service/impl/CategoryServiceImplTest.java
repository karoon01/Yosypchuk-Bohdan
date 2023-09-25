package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.exception.EntityAlreadyExistException;
import com.epam.spring.homework3.exception.EntityNotFoundException;
import com.epam.spring.homework3.model.DTO.CategoryDTO;
import com.epam.spring.homework3.model.entity.Category;
import com.epam.spring.homework3.repository.CategoryRepository;
import com.epam.spring.homework3.testUtils.TestCategoryDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.epam.spring.homework3.testUtils.TestCategoryDataUtil.MOCK_ID;
import static com.epam.spring.homework3.testUtils.TestCategoryDataUtil.MOCK_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void testCreateCategory() {
        //given
        Category category = TestCategoryDataUtil.createCategory();
        CategoryDTO categoryBody = TestCategoryDataUtil.createCategoryDto();
        when(categoryRepository.save(any())).thenReturn(category);

        //when
        categoryService.createCategory(categoryBody);

        //then
        assertThat(categoryBody, allOf(
                hasProperty("name", equalTo(category.getName()))
        ));
    }

    @Test
    void testCreateCategoryThrowsExceptionIfCategoryIsAlreadyExist() {
        //given
        Category category = TestCategoryDataUtil.createCategory();
        CategoryDTO categoryBody = TestCategoryDataUtil.createCategoryDto();

        //when
        when(categoryRepository.findCategoryByName(MOCK_NAME)).thenReturn(Optional.of(category));

        //then
        assertThrows(EntityAlreadyExistException.class, () -> categoryService.createCategory(categoryBody));
    }

    @Test
    void testGetAllCategories() {
        //given
        Category expectedCategory = TestCategoryDataUtil.createCategory();
        List<Category> categoryList = Collections.singletonList(expectedCategory);
        when(categoryRepository.findAll()).thenReturn(categoryList);

        //when
        List<CategoryDTO> categories = categoryService.getAllCategories();

        //then
        assertThat(categories, hasSize(1));
    }

    @Test
    void testGetCategoryByName() {
        //given
        Category expectedCategory = TestCategoryDataUtil.createCategory();
        when(categoryRepository.findCategoryByName(MOCK_NAME)).thenReturn(Optional.of(expectedCategory));

        //when
        CategoryDTO actualCategory = categoryService.getCategory(MOCK_NAME);

        //then
        assertThat(actualCategory, allOf(
              hasProperty("id", equalTo(actualCategory.getId())),
              hasProperty("name", equalTo(actualCategory.getName()))
        ));
    }

    @Test
    void testGetCategoryByNameThrowsExceptionIfCategoryDoesntExist() {
        //when
        when(categoryRepository.findCategoryByName(MOCK_NAME)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> categoryService.getCategory(MOCK_NAME));
    }

    @Test
    void testDeleteCategory() {
        //given
        Category category = TestCategoryDataUtil.createCategory();
        when(categoryRepository.findById(MOCK_ID)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).delete(any());

        //when
        categoryService.deleteCategory(MOCK_ID);

        //then
        verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    void testDeleteCategoryThrowsExceptionIfCategoryDoesntExist() {
        //when
        when(categoryRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> categoryService.deleteCategory(MOCK_ID));
    }
}