package com.example.invertech.dto;

import com.example.invertech.user.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLoggedDTO {
    private String firstname;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
}
