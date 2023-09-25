package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.exception.EntityNotFoundException;
import com.epam.spring.homework3.model.DTO.UserActivityTimeDTO;
import com.epam.spring.homework3.model.entity.Activity;
import com.epam.spring.homework3.model.entity.User;
import com.epam.spring.homework3.model.entity.UserActivityTime;
import com.epam.spring.homework3.repository.ActivityRepository;
import com.epam.spring.homework3.repository.TimeRepository;
import com.epam.spring.homework3.repository.UserRepository;
import com.epam.spring.homework3.testUtils.TestActivityDataUtil;
import com.epam.spring.homework3.testUtils.TestUserActivityTimeDataUtil;
import com.epam.spring.homework3.testUtils.TestUserDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.epam.spring.homework3.testUtils.TestUserActivityTimeDataUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserActivityTImeServiceImplTest {
    @InjectMocks
    private UserActivityTimeServiceImpl timeService;

    @Mock
    private TimeRepository timeRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ActivityRepository activityRepository;

    @Test
    void testSave() {
        //given
        UserActivityTime expectedTime = TestUserActivityTimeDataUtil.createUserActivityTime();
        when(timeRepository.save(any())).thenReturn(expectedTime);

        //when
        UserActivityTime actualTime = timeService.save(expectedTime);

        //then
        assertThat(actualTime, allOf(
                hasProperty("user", equalTo(expectedTime.getUser())),
                hasProperty("activity", equalTo(expectedTime.getActivity())),
                hasProperty("duration", equalTo(expectedTime.getDuration()))
        ));
    }

    @Test
    void testMarkTime() {
        //given
        UserActivityTime time = TestUserActivityTimeDataUtil.createUserActivityTime();
        UserActivityTimeDTO timeBody = TestUserActivityTimeDataUtil.createUserActivityTimeDto();
        User user = time.getUser();
        Activity activity = time.getActivity();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(activityRepository.findById(activity.getId())).thenReturn(Optional.of(activity));
        when(timeRepository.findByActivityIdAndUserId(activity.getId(), user.getId())).thenReturn(Optional.of(time));
        doNothing().when(timeRepository).markTime(MOCK_SESSION_DURATION, activity.getId(), user.getId());

        //when
        timeService.markTime(user.getId(), activity.getId(), timeBody);

        //then
        verify(timeRepository, times(1)).markTime(MOCK_SESSION_DURATION, activity.getId(), user.getId());
    }

    @Test
    void testMarkTimeThrowsExceptionIfUserDoesntExist() {
        //given
        UserActivityTimeDTO timeBody = TestUserActivityTimeDataUtil.createUserActivityTimeDto();

        //when
        when(userRepository.findById(MOCK_USER.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> timeService.markTime(MOCK_USER.getId(), MOCK_ACTIVITY.getId(), timeBody));
    }

    @Test
    void testMarkTimeThrowsExceptionIfActivityDoesntExist() {
        //given
        UserActivityTimeDTO timeBody = TestUserActivityTimeDataUtil.createUserActivityTimeDto();
        User user = TestUserDataUtil.createUser();

        //when
        when(userRepository.findById(MOCK_USER.getId())).thenReturn(Optional.of(user));
        when(activityRepository.findById(MOCK_ACTIVITY.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> timeService.markTime(MOCK_USER.getId(), MOCK_ACTIVITY.getId(), timeBody));
    }

    @Test
    void testMarkTimeThrowsExceptionIfTimeDoesntExist() {
        //given
        UserActivityTimeDTO timeBody = TestUserActivityTimeDataUtil.createUserActivityTimeDto();
        User user = TestUserDataUtil.createUser();
        Activity activity = TestActivityDataUtil.createActivity();

        //when
        when(userRepository.findById(MOCK_USER.getId())).thenReturn(Optional.of(user));
        when(activityRepository.findById(MOCK_ACTIVITY.getId())).thenReturn(Optional.of(activity));
        when(timeRepository.findByActivityIdAndUserId(MOCK_ACTIVITY.getId(), MOCK_USER.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> timeService.markTime(MOCK_USER.getId(), MOCK_ACTIVITY.getId(), timeBody));
    }

    @Test
    void testDelete() {
        //given
        UserActivityTime expectedTime = TestUserActivityTimeDataUtil.createUserActivityTime();
        User user = expectedTime.getUser();
        Activity activity = expectedTime.getActivity();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(activityRepository.findById(activity.getId())).thenReturn(Optional.of(activity));
        doNothing().when(timeRepository).deleteByUserIdAndActivityId(user.getId(), activity.getId());

        //when
        timeService.delete(user.getId(), activity.getId());

        //then
        verify(timeRepository, times(1)).deleteByUserIdAndActivityId(user.getId(), activity.getId());
    }

    @Test
    void testDeleteThrowsExceptionIfUserDoesntExist() {
        //when
        when(userRepository.findById(MOCK_USER.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> timeService.delete(MOCK_USER.getId(), MOCK_ACTIVITY.getId()));

    }

    @Test
    void testDeleteThrowsExceptionIfActivityDoesntExist() {
        //given
        User user = TestUserDataUtil.createUser();

        //when
        when(userRepository.findById(MOCK_USER.getId())).thenReturn(Optional.of(user));
        when(activityRepository.findById(MOCK_ACTIVITY.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> timeService.delete(MOCK_USER.getId(), MOCK_ACTIVITY.getId()));
    }
}