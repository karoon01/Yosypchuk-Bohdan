package com.epam.spring.homework3.testUtils;

import com.epam.spring.homework3.model.DTO.LoginRequestDTO;
import com.epam.spring.homework3.model.DTO.UserDTO;
import com.epam.spring.homework3.model.entity.Role;
import com.epam.spring.homework3.model.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUserDataUtil {
    public final static Long MOCK_ID = 1L;
    public final static String MOCK_FIRST_NAME = "Michael";
    public final static String MOCK_LAST_NAME = "Jackson";
    public final static String MOCK_EMAIL = "email@gmail.com";
    public final static String MOCK_PASSWORD = "12345678";
    public final static Role MOCK_ROLE = Role.USER;

    public final static String MOCK_UPDATE_FIRST_NAME = "Richard";
    public final static String MOCK_UPDATE_LAST_NAME = "Blackwater";
    public final static String MOCK_UPDATE_EMAIL = "email2@gmail.com";
    public final static String MOCK_UPDATE_PASSWORD = "123456789";

    public static User createUser() {
        return User.builder()
                .id(MOCK_ID)
                .firstName(MOCK_FIRST_NAME)
                .lastName(MOCK_LAST_NAME)
                .email(MOCK_EMAIL)
                .password(MOCK_PASSWORD)
                .role(MOCK_ROLE)
                .build();
    }

    public static UserDTO createUserDto() {
        return UserDTO.builder()
                .id(MOCK_ID)
                .firstName(MOCK_FIRST_NAME)
                .lastName(MOCK_LAST_NAME)
                .email(MOCK_EMAIL)
                .password(MOCK_PASSWORD)
                .role(MOCK_ROLE)
                .build();
    }

    public static UserDTO createUpdatedUserDto() {
        return UserDTO.builder()
                .id(MOCK_ID)
                .firstName(MOCK_UPDATE_FIRST_NAME)
                .lastName(MOCK_UPDATE_LAST_NAME)
                .email(MOCK_UPDATE_EMAIL)
                .password(MOCK_UPDATE_PASSWORD)
                .role(MOCK_ROLE)
                .build();
    }

    public static LoginRequestDTO createLoginRequestDto() {
        return LoginRequestDTO.builder()
                .email(MOCK_EMAIL)
                .password(MOCK_PASSWORD)
                .build();
    }

}