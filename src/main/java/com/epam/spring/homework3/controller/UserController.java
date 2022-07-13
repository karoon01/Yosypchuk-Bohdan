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

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;
    private final UserActivityTimeService timeService;

    @Override
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @Override
    public UserDTO updateUser(@PathVariable String id, @Valid @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public UserActivityTime markTime(@PathVariable Long userId, @PathVariable Long activityId, @Valid @RequestBody UserActivityTimeDTO timeDTO) {
        UserActivityTime time = timeService.markTime(userId, activityId, timeDTO);
        return time;
    }
}
