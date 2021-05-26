package com.itis.tasktracker.services;

import com.itis.tasktracker.dto.UserDto;
import com.itis.tasktracker.models.User;
import com.itis.tasktracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    @Override
    public boolean existUser(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent();
    }

    @Override
    public String generateSecurePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    @Override
    public UserDto signUpAndSave(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(generateSecurePassword(userDto.getPassword()))
                .isDeleted(false)
                .userRole(User.Role.USER)
                .userState(User.State.ACTIVE)
                .build();
        userRepository.save(user);

        return UserDto.from(user);
    }

    @Override
    public UserDto signInAndCheck(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user != null) {
            if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                return UserDto.from(user);
            }
        }
        return null;
    }

    @Override
    public UserDto ban(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setUserState(User.State.BANNED);
        userRepository.save(user);
        return UserDto.from(userRepository.save(user));
    }

    @Override
    public UserDto unban(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setUserState(User.State.ACTIVE);
        userRepository.save(user);
        return UserDto.from(userRepository.save(user));
    }
}
