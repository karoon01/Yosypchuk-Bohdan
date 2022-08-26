package com.epam.spring.homework3.model.DTO;

import com.epam.spring.homework3.model.entity.Category;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class ActivityDTO {
    private Long id;
    @NotBlank
    @Size(min = 4)
    @Size(max = 25)
    private String name;

    @Size(min = 5)
    @Size(max = 255)
    private String description;

    @NotBlank
    private Category category;
}
