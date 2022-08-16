package com.epam.spring.homework3.testUtils;

import com.epam.spring.homework3.model.DTO.ActivityDTO;
import com.epam.spring.homework3.model.entity.Activity;
import com.epam.spring.homework3.model.entity.Category;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestActivityDataUtil {
    public static final Long MOCK_ID = 1L;
    public static final String MOCK_NAME = "Activity";
    public static final String MOCK_DESCRIPTION = "Activity description";
    public static final Category MOCK_CATEGORY = TestCategoryDataUtil.createCategory();

    public static final String MOCK_UPDATE_NAME = "New Activity";
    public static final String MOCK_UPDATE_DESCRIPTION = "New Activity description";

    public static Activity createActivity() {
        return Activity.builder()
                .id(MOCK_ID)
                .name(MOCK_NAME)
                .description(MOCK_DESCRIPTION)
                .category(MOCK_CATEGORY)
                .build();
    }

    public static ActivityDTO createActivityDto() {
        return ActivityDTO.builder()
                .id(MOCK_ID)
                .name(MOCK_NAME)
                .description(MOCK_DESCRIPTION)
                .category(MOCK_CATEGORY)
                .build();
    }

    public static ActivityDTO createUpdatedActivityDto() {
        return ActivityDTO.builder()
                .id(MOCK_ID)
                .name(MOCK_UPDATE_NAME)
                .description(MOCK_UPDATE_DESCRIPTION)
                .category(MOCK_CATEGORY)
                .build();
    }
}