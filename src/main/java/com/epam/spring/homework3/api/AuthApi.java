package com.epam.spring.homework3.api;

import com.epam.spring.homework3.model.DTO.LoginRequestDTO;
import com.epam.spring.homework3.model.DTO.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("Authentication and authorization management API")
@RequestMapping("/api/v1/auth")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public interface AuthApi {

    @ApiOperation("Register user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    UserDTO register(@RequestBody @Valid UserDTO userDTO);

    @ApiOperation("Sign in user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    ResponseEntity<UserDTO> login(@RequestBody @Valid LoginRequestDTO request);
}
