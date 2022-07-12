package com.epam.spring.homework3.service;

import com.epam.spring.homework3.model.DTO.ActivityDTO;
import com.epam.spring.homework3.model.DTO.UserDTO;

import java.util.List;

public interface ActivityService {
    ActivityDTO createActivity(ActivityDTO activityDTO);

    ActivityDTO getActivity(Long id);

    ActivityDTO updateActivity(Long id, ActivityDTO activityDTO);

    void deleteActivity(Long id);

    List<ActivityDTO> getAllActivities();

    List<UserDTO> getAllActivityUsers(Long activityId);

}
