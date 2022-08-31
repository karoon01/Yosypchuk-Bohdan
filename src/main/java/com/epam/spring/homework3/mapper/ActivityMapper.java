package com.epam.spring.homework3.mapper;

import com.epam.spring.homework3.model.DTO.ActivityDTO;
import com.epam.spring.homework3.model.entity.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActivityMapper {
    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    Activity mapActivity(ActivityDTO activityDTO);

    ActivityDTO mapActivityDto(Activity activity);
}
