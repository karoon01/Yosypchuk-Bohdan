package com.epam.spring.homework3.mapper;

import com.epam.spring.homework3.model.DTO.CategoryDTO;
import com.epam.spring.homework3.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category mapCategory(CategoryDTO categoryDTO);
    CategoryDTO mapCategoryDto(Category category);
}
