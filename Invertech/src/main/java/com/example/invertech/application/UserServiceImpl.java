package com.example.invertech.application;


import com.example.invertech.domain.models.User;
import com.example.invertech.domain.puertos.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository IUserRepository;

    @Override
    public User saveUser(User user) {
        return IUserRepository.saveUser(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return IUserRepository.getUserById(id);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return IUserRepository.getAllUsers();
    }

    @Override
    public User updateUser(User user) {
        return IUserRepository.updateUser(user);
    }

    @Override
    public boolean deleateUser(Long id) {
        return IUserRepository.deleateUser(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return IUserRepository.getByEmail(email);
    }
}
