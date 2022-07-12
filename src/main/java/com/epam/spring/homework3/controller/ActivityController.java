package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.model.DTO.ActivityDTO;
import com.epam.spring.homework3.model.DTO.UserActivityTimeDTO;
import com.epam.spring.homework3.model.entity.UserActivityTime;
import com.epam.spring.homework3.service.ActivityService;
import com.epam.spring.homework3.service.UserActivityTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;
    private final UserActivityTimeService timeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/activity/{id}")
    public ActivityDTO getActivity(@PathVariable Long id) {
        return activityService.getActivity(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/activity/all")
    public List<ActivityDTO> getActivity() {
        return activityService.getAllActivities();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/admin/activity")
    public ActivityDTO createActivity(@RequestBody ActivityDTO activityDTO) {
        return activityService.createActivity(activityDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/admin/activity")
    public ActivityDTO updateActivity(@PathVariable Long id, @RequestBody ActivityDTO activityDTO) {
        return activityService.updateActivity(id, activityDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/admin/activity")
    public ResponseEntity<Void> removeActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/{userId}/activity/{activityId}/mark")
    public UserActivityTime markTime(@PathVariable Long userId, @PathVariable Long activityId, @RequestBody UserActivityTimeDTO timeDTO) {
        UserActivityTime time = timeService.markTime(userId, activityId, timeDTO);
        return time;
    }
}
