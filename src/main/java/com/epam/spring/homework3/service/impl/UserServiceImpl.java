package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.model.DTO.UserDTO;
import com.epam.spring.homework3.exception.EntityAlreadyExistException;
import com.epam.spring.homework3.exception.EntityNotFoundException;
import com.epam.spring.homework3.model.entity.Role;
import com.epam.spring.homework3.service.UserService;
import com.epam.spring.homework3.model.entity.User;
import com.epam.spring.homework3.repository.UserRepository;
import com.epam.spring.homework3.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();

        log.info("Register user: {}", userDTO);
        Optional<User> possibleUser = userRepository.findByEmail(email);
        if (possibleUser.isPresent()) {
            log.warn("User with email {} is already exist!", email);
            throw new EntityAlreadyExistException("User with email is already exist!");
        }

        User user = UserMapper.INSTANCE.mapUser(userDTO);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);

        log.info("Save user to database");
        userRepository.save(user);

        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        log.info("Get user by email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist!"));
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    public UserDTO getUser(Long id) {
        log.info("Get user by id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist!"));
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        log.info("Update user by id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        User updatedUser = User.builder()
                .id(id)
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(user.getRole())
                .build();

        userRepository.save(updatedUser);

        return UserMapper.INSTANCE.mapUserDto(updatedUser);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        log.info("Delete user by id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist!"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::mapUserDto)
                .collect(Collectors.toList());
    }
}
