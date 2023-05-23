package com.example.invertech.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IsFirstTimeResponse {
    private boolean firstTime;
}
