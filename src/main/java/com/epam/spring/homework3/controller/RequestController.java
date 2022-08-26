package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.api.RequestApi;
import com.epam.spring.homework3.model.DTO.RequestDTO;
import com.epam.spring.homework3.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RequestController implements RequestApi {

    private final RequestService requestService;

    @Override
    public List<RequestDTO> getAllRequests() {
        return requestService.getAllRequests();
    }

    @Override
    public List<RequestDTO> getAllUsersRequests(Long id) {
        return requestService.getAllUserRequests(id);
    }

    @Override
    public RequestDTO createRequest(Long userId, Long activityId) {
        return requestService.createRequest(userId, activityId);
    }

    @Override
    public ResponseEntity<Void> deleteRequest(Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> approveRequest(Long id) {
        requestService.approveRequest(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> rejectRequest(Long id) {
        requestService.rejectRequest(id);
        return ResponseEntity.noContent().build();
    }

}
