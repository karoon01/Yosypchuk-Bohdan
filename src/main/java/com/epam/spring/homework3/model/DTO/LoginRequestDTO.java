package com.epam.spring.homework3.model.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
