package com.akouendy.khaerries.custom_jwt.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
