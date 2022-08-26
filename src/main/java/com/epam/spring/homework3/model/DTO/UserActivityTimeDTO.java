package com.epam.spring.homework3.model.DTO;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserActivityTimeDTO {
    private Long userId;
    private Long activityId;
    @NotBlank
    private String startTime;
    @NotBlank
    private String endTime;
}
