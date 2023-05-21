package com.example.invertech.user;

import com.example.invertech.dto.UserLoggedDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserLoggedDTO getUserData(String email){
        User user = repository.findByEmail(email).get();
        return UserLoggedDTO.builder()
                .firstname(user.getFirstname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
