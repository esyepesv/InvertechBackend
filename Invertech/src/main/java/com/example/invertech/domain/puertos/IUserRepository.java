package com.example.invertech.domain.puertos;

import com.example.invertech.domain.models.User;

import java.util.Optional;

public interface IUserRepository {
    User saveUser(User user);
    Optional<User> getUserById(Long id);
    Iterable<User> getAllUsers();
    User updateUser(User user);
    boolean deleateUser(Long id);
}
