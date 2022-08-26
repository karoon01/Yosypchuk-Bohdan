package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.exception.EntityAlreadyExistException;
import com.epam.spring.homework3.model.DTO.ActivityDTO;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        String name = activityDTO.getName();
        Optional<Activity> possibleActivity = activityRepository.findActivityByName(name);

        if(possibleActivity.isPresent()) {
            log.warn("Activity with name: \"{}\" is already exist", name);
            throw new EntityAlreadyExistException("Activity is already exist!");
        }
        log.info("Create activity with name: {}", name);
        Activity activity = ActivityMapper.INSTANCE.mapActivity(activityDTO);
        activityRepository.save(activity);

        return activityDTO;
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
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity doesn't exist"));

        Activity updatedActivity = Activity.builder()
                .id(activity.getId())
                .name(activityDTO.getName())
                .description(activityDTO.getDescription())
                .category(activity.getCategory())
                .build();

        activityRepository.save(updatedActivity);

        return ActivityMapper.INSTANCE.mapActivityDto(updatedActivity);
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
