package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.model.DTO.RequestDTO;
import com.epam.spring.homework3.exception.EntityNotFoundException;
import com.epam.spring.homework3.model.entity.*;
import com.epam.spring.homework3.repository.ActivityRepository;
import com.epam.spring.homework3.repository.TimeRepository;
import com.epam.spring.homework3.repository.UserRepository;
import com.epam.spring.homework3.service.RequestService;
import com.epam.spring.homework3.repository.RequestRepository;
import com.epam.spring.homework3.mapper.RequestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final TimeRepository timeRepository;

    private static final String DEFAULT_TIME = "PT0S";

    @Transactional
    @Override
    public RequestDTO createRequest(Long userId, Long activityId) {
        log.info("Get user with id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User is not found!"));

        log.info("Get activity with id: {}", activityId);
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new EntityNotFoundException("Activity is not found!"));

        log.info("Create request for user with id: {} and activity with id: {}", userId, activityId);
        Request request = Request.builder()
                .requestDate(new Date())
                .status(Status.PENDING)
                .activity(activity)
                .user(user)
                .build();

        requestRepository.save(request);

        return RequestMapper.INSTANCE.mapRequestDTO(request);
    }

    @Override
    public List<RequestDTO> getAllRequests() {
        log.info("Get all requests");
        return requestRepository.findAll()
                .stream()
                .map(RequestMapper.INSTANCE::mapRequestDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> getAllUserRequests(Long id) {
        log.info("Get all user's requests with id: {}", id);
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User is not found!"));

        return requestRepository.getAllRequestsByUserId(id)
                .stream()
                .map(RequestMapper.INSTANCE::mapRequestDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void approveRequest(Long id) {
        log.info("Approve request with id: {}", id);

        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Request is not found!"));

        UserActivityTime time = UserActivityTime.builder()
                .id(new UserActivityTimeKey(request.getActivity().getId(), request.getUser().getId()))
                .activity(request.getActivity())
                .user(request.getUser())
                .duration(DEFAULT_TIME)
                .build();

        requestRepository.updateRequestStatusById(Status.APPROVED, id);
        timeRepository.save(time);
    }

    @Transactional
    @Override
    public void rejectRequest(Long id) {
        log.info("Reject request with id: {}", id);
        requestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Request is not found!"));
        requestRepository.updateRequestStatusById(Status.REJECTED, id);
    }

    @Transactional
    @Override
    public void deleteRequest(Long id) {
        log.info("Delete request with id: {}", id);
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Request is not found!"));
        requestRepository.delete(request);
    }
}
