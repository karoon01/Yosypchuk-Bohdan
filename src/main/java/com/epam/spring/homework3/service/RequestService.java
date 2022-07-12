package com.epam.spring.homework3.service;

import com.epam.spring.homework3.model.DTO.RequestDTO;

import java.util.List;

public interface RequestService {
    RequestDTO createRequest(Long userId, Long activityId);

    List<RequestDTO> getAllRequests();

    List<RequestDTO> getAllUserRequests(Long id);

    void approveRequest(Long id);

    void rejectRequest(Long id);

    void deleteRequest(Long id);
}
