package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.exception.EntityAlreadyExistException;
import com.epam.spring.homework3.exception.EntityNotFoundException;
import com.epam.spring.homework3.model.DTO.ActivityDTO;
import com.epam.spring.homework3.model.entity.Activity;
import com.epam.spring.homework3.repository.ActivityRepository;
import com.epam.spring.homework3.testUtils.TestActivityDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.epam.spring.homework3.testUtils.TestActivityDataUtil.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(MockitoExtension.class)
public class ActivityServiceImplTest {

    @InjectMocks
    private ActivityServiceImpl activityService;

    @Mock
    private ActivityRepository activityRepository;

    @Test
    void testCreateActivity() {
        //given
        Activity expectedActivity = TestActivityDataUtil.createActivity();
        ActivityDTO activityBody = TestActivityDataUtil.createActivityDto();
        when(activityRepository.save(any())).thenReturn(expectedActivity);

        //when
        activityService.createActivity(activityBody);

        //then
        assertThat(activityBody, allOf(
                hasProperty("name", equalTo(expectedActivity.getName())),
                hasProperty("description", equalTo(expectedActivity.getDescription())),
                hasProperty("category", equalTo(expectedActivity.getCategory()))
        ));
    }

    @Test
    void testCreateActivityThrowsExceptionIfActivityIsAlreadyExist() {
        //given
        Activity activity = TestActivityDataUtil.createActivity();
        ActivityDTO activityBody = TestActivityDataUtil.createActivityDto();
        //when
        when(activityRepository.findActivityByName(MOCK_NAME)).thenReturn(Optional.of(activity));
        //then
        assertThrows(EntityAlreadyExistException.class, () -> activityService.createActivity(activityBody));
    }

    @Test
    void testGetActivityById() {
        //given
        Activity expectedActivity = TestActivityDataUtil.createActivity();
        when(activityRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedActivity));

        //when
        ActivityDTO actualActivity = activityService.getActivity(MOCK_ID);

        //then
        assertEquals(expectedActivity.getId(), actualActivity.getId());
        assertThat(actualActivity, allOf(
                hasProperty("name", equalTo(expectedActivity.getName())),
                hasProperty("description", equalTo(expectedActivity.getDescription())),
                hasProperty("category", equalTo(expectedActivity.getCategory()))
        ));
    }

    @Test
    void testGetActivityByIdThrowsExceptionIfActivityDoesntExist() {
        //when
        when(activityRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> activityService.getActivity(MOCK_ID));
    }

    @Test
    void testUpdateActivity() {
        //given
        Activity expectedActivity = TestActivityDataUtil.createActivity();
        ActivityDTO activityBody = TestActivityDataUtil.createUpdatedActivityDto();

        when(activityRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedActivity));
        when(activityRepository.save(any())).thenReturn(expectedActivity);

        //when
        ActivityDTO updatedActivity = activityService.updateActivity(MOCK_ID, activityBody);

        //then
        assertThat(updatedActivity, allOf(
                hasProperty("name", equalTo(activityBody.getName())),
                hasProperty("description", equalTo(activityBody.getDescription())),
                hasProperty("category", equalTo(activityBody.getCategory()))
        ));
    }

    @Test
    void testUpdateActivityThrowsExceptionIfActivityDoesntExist() {
        //given
        ActivityDTO activityBody = TestActivityDataUtil.createActivityDto();

        //when
        when(activityRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> activityService.updateActivity(MOCK_ID, activityBody));
    }

    @Test
    void testDeleteActivity() {
        //given
        Activity activity = TestActivityDataUtil.createActivity();
        when(activityRepository.findById(MOCK_ID)).thenReturn(Optional.of(activity));
        doNothing().when(activityRepository).delete(any());

        //when
        activityService.deleteActivity(MOCK_ID);

        //then
        verify(activityRepository, times(1)).delete(activity);
    }

    @Test
    void testDeleteActivityThrowsExceptionIfActivityDoesntExist() {
        //when
        when(activityRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> activityService.deleteActivity(MOCK_ID));
    }

    @Test
    void testGetAllActivities() {
        //given
        Activity expectedActivity = TestActivityDataUtil.createActivity();
        List<Activity> activityList = Collections.singletonList(expectedActivity);
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        when(activityRepository.findAll(sort)).thenReturn(activityList);

        //when
        List<ActivityDTO> activities = activityService.getAllActivities();

        //then
        assertThat(activities, hasSize(1));
    }
}