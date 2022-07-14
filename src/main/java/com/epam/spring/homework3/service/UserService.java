package com.epam.spring.homework3.service;

import com.epam.spring.homework3.model.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);

    UserDTO getUserByEmail(String email);

    UserDTO getUser(Long id);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    List<UserDTO> getAllUsers();
}
