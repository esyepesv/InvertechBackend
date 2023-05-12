package com.example.invertech.application;

import com.example.invertech.domain.models.User;

import java.util.Optional;

public interface IUserService {
    User saveUser(User user);
    Optional<User> getUserById(Long id);
    Iterable<User> getAllUsers();
    User updateUser(User user);
    boolean deleateUser(Long id);
    Optional<User> getUserByEmail(String email);
}
