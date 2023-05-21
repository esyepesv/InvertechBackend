package com.example.invertech.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserLoggedRequest {
    @JsonProperty("email")
    private String email;
}
