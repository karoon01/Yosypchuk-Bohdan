package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.api.ActivityApi;
import com.epam.spring.homework3.model.DTO.ActivityDTO;
import com.epam.spring.homework3.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ActivityController implements ActivityApi {

    private final ActivityService activityService;

    @Override
    public ActivityDTO getActivity(Long id) {
        return activityService.getActivity(id);
    }

    @Override
    public List<ActivityDTO> getAllActivities() {
        return activityService.getAllActivities();
    }

    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        return activityService.createActivity(activityDTO);
    }

    @Override
    public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
        return activityService.updateActivity(id, activityDTO);
    }

    @Override
    public ResponseEntity<Void> deleteActivity(Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}
