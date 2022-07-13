package com.epam.spring.homework3.api;

import com.epam.spring.homework3.model.DTO.LoginRequestDTO;
import com.epam.spring.homework3.model.DTO.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api("Authentication and authorization management API")
@RequestMapping("/api/v1/auth")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public interface AuthApi {

    @ApiOperation("Register user")
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/register")
    UserDTO register(@RequestBody UserDTO userDTO);

    @ApiOperation("Sign in user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/login")
    ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO request);
}
