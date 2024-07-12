package com.akouendy.khaerries.custom_jwt.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akouendy.khaerries.custom_jwt.dto.ApiResponse;
import com.akouendy.khaerries.custom_jwt.dto.LoginRequest;
import com.akouendy.khaerries.custom_jwt.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class UserController {
    
    	private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> login(
			@RequestBody LoginRequest loginRequest) {      
		return ResponseEntity
            .ok(userService.login(loginRequest));
	}
    
}
