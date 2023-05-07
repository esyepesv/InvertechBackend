package com.example.invertech.application;


import com.example.invertech.domain.dtos.UpdateEmailDTO;
import com.example.invertech.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUseCase {
    @Autowired
    private IUserService userService;
    public User updateEmail(UpdateEmailDTO updateEmailDTO){
        Optional<User> user = userService.getUserById(updateEmailDTO.getId());
        user.get().setEmail(updateEmailDTO.getEmail());
        userService.saveUser(user.get());
        return user.get();
    }
}
