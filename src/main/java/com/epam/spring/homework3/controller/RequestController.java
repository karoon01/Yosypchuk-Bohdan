package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.model.DTO.RequestDTO;
import com.epam.spring.homework3.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/admin/request/all")
    public List<RequestDTO> getAllRequests() {
        return requestService.getAllRequests();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}/requests")
    public List<RequestDTO> getAllUsersRequests(@PathVariable Long id) {
        return requestService.getAllUserRequests(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/{userId}/request/activity/{activityId}")
    public RequestDTO createRequest(@PathVariable Long userId, @PathVariable Long activityId) {
        return requestService.createRequest(userId, activityId);
    }

    @DeleteMapping("/user/{id}/request/{requestId}/delete")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id, @PathVariable Long requestId) {
        requestService.deleteRequest(requestId);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/admin/request/{id}/approve")
    public ResponseEntity<Void> approveRequest(@PathVariable Long id) {
        requestService.approveRequest(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/admin/request/{id}/reject")
    public ResponseEntity<Void> rejectRequest(@PathVariable Long id) {
        requestService.rejectRequest(id);
        return ResponseEntity.noContent().build();
    }

}
