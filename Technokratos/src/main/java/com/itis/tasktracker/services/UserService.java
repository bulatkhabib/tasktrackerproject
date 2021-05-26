package com.itis.tasktracker.services;

import com.itis.tasktracker.dto.UserDto;
import com.itis.tasktracker.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getById(Long id);

    User getByEmail(String email);

    boolean existUser(String email);

    String generateSecurePassword(String password);

    User loadUserByUsername(String username);

    UserDto signUpAndSave(UserDto userDto);

    UserDto signInAndCheck(UserDto userDto);

    UserDto ban(UserDto userDto);

    UserDto unban(UserDto userDto);
}
