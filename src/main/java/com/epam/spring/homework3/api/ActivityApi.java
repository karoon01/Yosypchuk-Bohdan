package com.epam.spring.homework3.api;

import com.epam.spring.homework3.model.DTO.ActivityDTO;
import com.epam.spring.homework3.model.DTO.UserActivityTimeDTO;
import com.epam.spring.homework3.model.entity.UserActivityTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Activity management API")
@RequestMapping("/api/v1/activity")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public interface ActivityApi {

    @ApiOperation("Get activity by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    ActivityDTO getActivity(@PathVariable Long id);

    @ApiOperation("Get all activities")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    List<ActivityDTO> getAllActivities();

    @ApiOperation("Create activity")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    ActivityDTO createActivity(@RequestBody ActivityDTO activityDTO);

    @ApiOperation("Update activity")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    ActivityDTO updateActivity(@PathVariable Long id, @RequestBody ActivityDTO activityDTO);

    @ApiOperation("Delete activity")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteActivity(@PathVariable Long id);
}
