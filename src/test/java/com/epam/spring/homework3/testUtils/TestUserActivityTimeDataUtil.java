package com.epam.spring.homework3.testUtils;

import com.epam.spring.homework3.model.DTO.UserActivityTimeDTO;
import com.epam.spring.homework3.model.entity.Activity;
import com.epam.spring.homework3.model.entity.User;
import com.epam.spring.homework3.model.entity.UserActivityTime;
import com.epam.spring.homework3.model.entity.UserActivityTimeKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUserActivityTimeDataUtil {
    public static final User MOCK_USER = TestUserDataUtil.createUser();
    public static final Activity MOCK_ACTIVITY = TestActivityDataUtil.createActivity();
    public static final String MOCK_DEFAULT_DURATION = "PT0S";
    public static final String MOCK_START_TIME = "12:40:17";
    public static final String MOCK_END_TIME = "14:21:17";
    public static final String MOCK_SESSION_DURATION = "PT1H41M";

    public static UserActivityTime createUserActivityTime() {
        return UserActivityTime.builder()
                .id(new UserActivityTimeKey(MOCK_ACTIVITY.getId(), MOCK_USER.getId()))
                .activity(MOCK_ACTIVITY)
                .user(MOCK_USER)
                .duration(MOCK_DEFAULT_DURATION)
                .build();
    }

    public static UserActivityTime updatedUserActivityTime() {
        return UserActivityTime.builder()
                .id(new UserActivityTimeKey(MOCK_ACTIVITY.getId(), MOCK_USER.getId()))
                .activity(MOCK_ACTIVITY)
                .user(MOCK_USER)
                .duration(MOCK_SESSION_DURATION)
                .build();
    }

    public static UserActivityTimeDTO createUserActivityTimeDto() {
        return UserActivityTimeDTO.builder()
                .activityId(MOCK_ACTIVITY.getId())
                .userId(MOCK_USER.getId())
                .startTime(MOCK_START_TIME)
                .endTime(MOCK_END_TIME)
                .build();
    }
}