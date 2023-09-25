package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.exception.EntityNotFoundException;
import com.epam.spring.homework3.model.DTO.RequestDTO;
import com.epam.spring.homework3.model.entity.Activity;
import com.epam.spring.homework3.model.entity.Request;
import com.epam.spring.homework3.model.entity.User;
import com.epam.spring.homework3.model.entity.UserActivityTime;
import com.epam.spring.homework3.repository.ActivityRepository;
import com.epam.spring.homework3.repository.RequestRepository;
import com.epam.spring.homework3.repository.TimeRepository;
import com.epam.spring.homework3.repository.UserRepository;
import com.epam.spring.homework3.testUtils.TestRequestDataUtil;
import com.epam.spring.homework3.testUtils.TestUserActivityTimeDataUtil;
import com.epam.spring.homework3.testUtils.TestUserDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.epam.spring.homework3.testUtils.TestRequestDataUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestServiceImplTest {

    @InjectMocks
    private RequestServiceImpl requestService;

    @Mock
    private RequestRepository requestRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ActivityRepository activityRepository;
    @Mock
    private TimeRepository timeRepository;

    @Test
    void testCreateRequest() {
        //given
        Request expectedRequest = TestRequestDataUtil.createRequest();
        User requestUser = expectedRequest.getUser();
        Activity requestActivity = expectedRequest.getActivity();

        when(userRepository.findById(MOCK_USER.getId())).thenReturn(Optional.of(requestUser));
        when(activityRepository.findById(MOCK_ACTIVITY.getId())).thenReturn(Optional.of(requestActivity));
        when(requestRepository.save(any())).thenReturn(expectedRequest);

        //when
        RequestDTO actualRequest = requestService.createRequest(requestUser.getId(), requestActivity.getId());

        //then
        assertThat(actualRequest, allOf(
                hasProperty("status", equalTo(expectedRequest.getStatus())),
                hasProperty("user", equalTo(expectedRequest.getUser())),
                hasProperty("activity", equalTo(expectedRequest.getActivity()))
        ));
    }

    @Test
    void testCreateRequestThrowsExceptionIfUserDoesntExist() {
        //when
        when(userRepository.findById(MOCK_USER.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> requestService.createRequest(MOCK_USER.getId(), MOCK_ACTIVITY.getId()));
    }

    @Test
    void testCreateRequestThrowsExceptionIfActivityDoesntExist() {
        //given
        User user = TestUserDataUtil.createUser();

        //when
        when(userRepository.findById(MOCK_USER.getId())).thenReturn(Optional.of(user));
        when(activityRepository.findById(MOCK_ACTIVITY.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> requestService.createRequest(MOCK_USER.getId(), MOCK_ACTIVITY.getId()));
    }

    @Test
    void testGetAllRequests() {
        //given
        Request request = TestRequestDataUtil.createRequest();
        List<Request> requestList = Collections.singletonList(request);
        when(requestRepository.findAll()).thenReturn(requestList);

        //when
        List<RequestDTO> requests = requestService.getAllRequests();

        //then
        assertThat(requests, hasSize(1));
    }

    @Test
    void testGetAllUserRequests() {
        //given
        Request request = TestRequestDataUtil.createRequest();
        User user = TestUserDataUtil.createUser();
        List<Request> userRequestsList = Collections.singletonList(request);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(requestRepository.getAllRequestsByUserId(user.getId())).thenReturn(userRequestsList);

        //when
        List<RequestDTO> requests = requestService.getAllUserRequests(user.getId());

        //then
        assertThat(requests, hasSize(1));
    }

    @Test
    void testGetAllUserRequestsThrowsExceptionIfUserDoesntExist() {
        //when
        when(userRepository.findById(MOCK_USER.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> requestService.getAllUserRequests(MOCK_USER.getId()));
    }

    @Test
    void testGetAllUserRequestsEmptyList() {
        //given
        User user = TestUserDataUtil.createUser();

        //when
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(requestRepository.getAllRequestsByUserId(user.getId())).thenReturn(Collections.EMPTY_LIST);
        List<RequestDTO> requests = requestService.getAllUserRequests(user.getId());

        //then
        assertThat(requests, hasSize(0));
    }

    @Test
    void testApproveRequest() {
        //given
        Request request = TestRequestDataUtil.createRequest();
        UserActivityTime time = TestUserActivityTimeDataUtil.createUserActivityTime();

        when(requestRepository.findById(MOCK_ID)).thenReturn(Optional.of(request));
        doNothing().when(requestRepository).updateRequestStatusById(MOCK_APPROVED_STATUS, MOCK_ID);
        when(timeRepository.save(any())).thenReturn(time);

        //when
        requestService.approveRequest(MOCK_ID);

        //then
        verify(requestRepository, times(1)).updateRequestStatusById(MOCK_APPROVED_STATUS, MOCK_ID);
    }

    @Test
    void testApproveRequestThrowsExceptionIfRequestDoesntExist() {
        //when
        when(requestRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> requestService.approveRequest(MOCK_ID));
    }

    @Test
    void testRejectRequest() {
        //given
        Request request = TestRequestDataUtil.createRequest();
        when(requestRepository.findById(MOCK_ID)).thenReturn(Optional.of(request));
        doNothing().when(requestRepository).updateRequestStatusById(MOCK_REJECTED_STATUS, MOCK_ID);

        //when
        requestService.rejectRequest(MOCK_ID);

        //then
        verify(requestRepository, times(1)).updateRequestStatusById(MOCK_REJECTED_STATUS, MOCK_ID);
    }

    @Test
    void testRejectRequestThrowsExceptionIfRequestDoesntExist() {
        //when
        when(requestRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> requestService.rejectRequest(MOCK_ID));
    }

    @Test
    void testDeleteRequest() {
        //given
        Request request = TestRequestDataUtil.createRequest();
        when(requestRepository.findById(MOCK_ID)).thenReturn(Optional.of(request));
        doNothing().when(requestRepository).delete(any());

        //when
        requestService.deleteRequest(MOCK_ID);

        //then
        verify(requestRepository, times(1)).delete(request);
    }

    @Test
    void testDeleteRequestThrowsExceptionIfRequestDoesntExist() {
        //when
        when(requestRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> requestService.deleteRequest(MOCK_ID));
    }
}