package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.api.UserApi;
import com.epam.spring.homework3.model.DTO.UserActivityTimeDTO;
import com.epam.spring.homework3.model.DTO.UserDTO;
import com.epam.spring.homework3.model.entity.UserActivityTime;
import com.epam.spring.homework3.service.UserActivityTimeService;
import com.epam.spring.homework3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {

    private final UserService userService;
    private final UserActivityTimeService timeService;

    @Override
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userService.getUser(id);
    }

    @Override
    public UserDTO updateUser(Long id,UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public UserActivityTime markTime(Long userId, Long activityId, UserActivityTimeDTO timeDTO) {
        UserActivityTime time = timeService.markTime(userId, activityId, timeDTO);
        return time;
    }
}
