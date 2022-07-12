package com.epam.spring.homework3.model.DTO;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class CategoryDTO {
    private Long id;
    @NotBlank
    @Size(min = 4)
    @Size(max = 25)
    private String name;
}
