package com.example.practice.global.auth.controller;

import com.example.practice.global.auth.dto.request.LoginRequestDto;
import com.example.practice.global.auth.dto.request.SignupRequestDto;
import com.example.practice.global.auth.dto.response.SignupResponseDto;
import com.example.practice.global.auth.dto.response.TokenResponseDto;
import com.example.practice.global.auth.service.AuthService;
import com.example.practice.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthContoller {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponseDto>> signup(@Valid @RequestBody SignupRequestDto requestDto){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ApiResponse.ok(
                authService.signup(requestDto)
            ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponseDto>> login(
        @Valid @RequestBody LoginRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.ok(authService.login(requestDto)));
    }
}
