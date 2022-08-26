package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.model.DTO.UserActivityTimeDTO;
import com.epam.spring.homework3.exception.EntityNotFoundException;
import com.epam.spring.homework3.model.entity.Activity;
import com.epam.spring.homework3.model.entity.User;
import com.epam.spring.homework3.model.entity.UserActivityTime;
import com.epam.spring.homework3.model.entity.UserActivityTimeKey;
import com.epam.spring.homework3.repository.ActivityRepository;
import com.epam.spring.homework3.repository.TimeRepository;
import com.epam.spring.homework3.repository.UserRepository;
import com.epam.spring.homework3.service.UserActivityTimeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalTime;

@Slf4j
@Service
@AllArgsConstructor
public class UserActivityTimeServiceImpl implements UserActivityTimeService {

    private final TimeRepository timeRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    @Override
    public UserActivityTime save(UserActivityTime time) {
        log.info("Save UserActivityTime: {}", time);
        return timeRepository.save(time);
    }

    @Transactional
    @Override
    public UserActivityTime markTime(Long userId, Long activityId, UserActivityTimeDTO timeDTO) {
        LocalTime startTime = LocalTime.parse(timeDTO.getStartTime());
        LocalTime endTime = LocalTime.parse(timeDTO.getEndTime());
        Duration sessionDuration = Duration.between(startTime, endTime);

        log.info("Get user with id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User is not found!"));

        log.info("Get activity with id: {}", activityId);
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new EntityNotFoundException("Activity is not found!"));

        log.info("Get UserActivityTime for user with id: {} for activity with id: {}", user.getId(), activity.getId());
        UserActivityTime time = timeRepository.findByActivityIdAndUserId(activity.getId(), user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Activity is not found!"));

        String updatedDuration = Duration.parse(time.getDuration()).plus(sessionDuration).toString();

        UserActivityTime updatedTime = UserActivityTime.builder()
                .id(new UserActivityTimeKey(activity.getId(), user.getId()))
                .user(user)
                .activity(activity)
                .duration(updatedDuration)
                .build();

        log.info("Mark time for user with id: {} for activity with id: {}", user.getId(), activity.getId());
        timeRepository.markTime(updatedDuration, activity.getId(), user.getId());

        return updatedTime;
    }

    @Override
    public void delete(Long userId, Long activityId) {
        log.info("Delete UserActivityTime for user with id: {} and activity with id: {}", userId, activityId);
        timeRepository.deleteByUserIdAndActivityId(userId, activityId);
    }
}
