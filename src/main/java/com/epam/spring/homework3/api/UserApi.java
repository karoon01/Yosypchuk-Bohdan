package com.epam.spring.homework3.api;

import com.epam.spring.homework3.model.DTO.UserActivityTimeDTO;
import com.epam.spring.homework3.model.DTO.UserDTO;
import com.epam.spring.homework3.model.entity.UserActivityTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User management API")
@RequestMapping("/api/v1/user")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public interface UserApi {

    @ApiOperation("Get all users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    List<UserDTO> getAllUsers();

    @ApiOperation("Get user by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id);

    @ApiOperation("Update user")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    UserDTO updateUser(@PathVariable String id, @RequestBody UserDTO userDTO);

    @ApiOperation("Delete user")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id);

    @ApiOperation("Mark time for activity")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}/activity/{activityId}/mark")
    UserActivityTime markTime(@PathVariable Long userId, @PathVariable Long activityId, @RequestBody UserActivityTimeDTO timeDTO);
}
