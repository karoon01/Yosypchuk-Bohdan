package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.model.DTO.ActivityDTO;
import com.epam.spring.homework3.model.DTO.UserDTO;
//import com.epam.spring.homework3.repository.TimeRepository;
import com.epam.spring.homework3.repository.TimeRepository;
import com.epam.spring.homework3.service.ActivityService;
import com.epam.spring.homework3.exception.EntityNotFoundException;
import com.epam.spring.homework3.mapper.ActivityMapper;
import com.epam.spring.homework3.model.entity.Activity;
import com.epam.spring.homework3.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        log.info("Create activity with id: {}", activityDTO.getId());
        Activity activity = ActivityMapper.INSTANCE.mapActivity(activityDTO);

        activityRepository.save(activity);

        return ActivityMapper.INSTANCE.mapActivityDto(activity);
    }

    @Override
    public ActivityDTO getActivity(Long id) {
        log.info("Get activity with id: {}", id);
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity doesn't exist!"));

        return ActivityMapper.INSTANCE.mapActivityDto(activity);
    }

    @Override
    public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
        log.info("Update activity with id: {}", id);
        Activity activity = ActivityMapper.INSTANCE.mapActivity(activityDTO);
        activityRepository.save(activity);

        return ActivityMapper.INSTANCE.mapActivityDto(activity);
    }

    @Transactional
    @Override
    public void deleteActivity(Long id) {
        log.info("Delete activity with id: {}", id);
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity doesn't exist!"));

        activityRepository.delete(activity);
    }

    @Override
    public List<ActivityDTO> getAllActivities() {
        log.info("Get all activities");
        return activityRepository.findAll()
                .stream()
                .map(ActivityMapper.INSTANCE::mapActivityDto)
                .collect(Collectors.toList());
    }
}
