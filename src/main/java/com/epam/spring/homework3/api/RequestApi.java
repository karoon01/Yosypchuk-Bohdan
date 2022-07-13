package com.epam.spring.homework3.api;

import com.epam.spring.homework3.model.DTO.RequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Request management API")
@RequestMapping("/api/v1/request")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public interface RequestApi {

    @ApiOperation("Get all requests")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    List<RequestDTO> getAllRequests();

    @ApiOperation("Get user's requests")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all/user/{id}")
    List<RequestDTO> getAllUsersRequests(@PathVariable Long id);

    @ApiOperation("Create request")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/{userId}/activity/{activityId}")
    RequestDTO createRequest(@PathVariable Long userId, @PathVariable Long activityId);

    @ApiOperation("Delete request")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRequest(@PathVariable Long id);

    @ApiOperation("Approve request by id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/approve")
    ResponseEntity<Void> approveRequest(@PathVariable Long id);

    @ApiOperation("Reject request by id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/reject")
     ResponseEntity<Void> rejectRequest(@PathVariable Long id);
}
