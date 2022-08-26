package com.epam.spring.homework3.service;

import com.epam.spring.homework3.model.DTO.UserActivityTimeDTO;
import com.epam.spring.homework3.model.entity.UserActivityTime;

public interface UserActivityTimeService {
    UserActivityTime save(UserActivityTime time);
    
    UserActivityTime markTime(Long userId, Long activityId, UserActivityTimeDTO timeDTO);

    void delete(Long userId, Long activityId);
}
