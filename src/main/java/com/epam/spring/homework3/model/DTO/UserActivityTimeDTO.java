package com.epam.spring.homework3.model.DTO;

import com.epam.spring.homework3.validation.TimeFormatValidation;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserActivityTimeDTO {
    private Long userId;

    private Long activityId;

    @TimeFormatValidation(message = "${time.format}")
    @NotBlank(message = "${time.start.not-blank}")
    private String startTime;

    @TimeFormatValidation(message = "${time.format}")
    @NotBlank(message = "${time.end.not-blank}")
    private String endTime;
}
