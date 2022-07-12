package com.epam.spring.homework3.mapper;

import com.epam.spring.homework3.model.DTO.UserDTO;
import com.epam.spring.homework3.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUser(UserDTO userDTO);

    UserDTO mapUserDto(User user);
}
