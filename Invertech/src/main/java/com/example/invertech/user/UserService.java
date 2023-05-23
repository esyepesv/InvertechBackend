package com.example.invertech.user;

import com.example.invertech.dto.IsFirstTimeResponse;
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
                .isPremium(user.isPremium())
                .firstTime(user.isFirstTime())
                .build();
    }
    public UserLoggedDTO setUserPremium(String email){
        System.out.println("Usuario premium::: " + repository.findByEmail(email).get().isPremium());
        User user = repository.findByEmail(email).get();
        user.setPremium(true);
        user = repository.save(user);
        System.out.println("Usuario premium::: " + user.isPremium());
        return UserLoggedDTO.builder()
                .firstname(user.getFirstname())
                .email(user.getEmail())
                .role(user.getRole())
                .isPremium(user.isPremium())
                .build();
    }

    public IsFirstTimeResponse isFirstTime(String email) {
        User user = repository.findByEmail(email).get();
        if (user.isFirstTime()) {
            return IsFirstTimeResponse.builder()
                    .firstTime(true)
                    .build();
        }else{
            return IsFirstTimeResponse.builder()
                    .firstTime(false)
                    .build();
        }
    }

    public IsFirstTimeResponse setFirstTimeFalse(String email) {
        User user = repository.findByEmail(email).get();
        user.setFirstTime(false);
        user = repository.save(user);
        return IsFirstTimeResponse.builder()
                .firstTime(false)
                .build();
    }
}
