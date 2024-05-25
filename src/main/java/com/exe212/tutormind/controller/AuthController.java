package com.exe212.tutormind.controller;

import com.exe212.tutormind.Model.DTO.LoginRequestDTO;
import com.exe212.tutormind.Model.DTO.RegisterRequestDTO;
import com.exe212.tutormind.Model.DTO.ResponseObjectDTO;
import com.exe212.tutormind.Model.Message;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.service.JwtService;
import com.exe212.tutormind.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    @Operation(summary = "Login endpoint",
            description= "User must exist")
    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginRequestDTO loginUser) throws Exception {

        User user = userService.login(loginUser.getEmail(), loginUser.getPassword());


        String refreshToken = jwtService.generateRefreshToken(user);
        String accessToken = jwtService.generateAccessToken(refreshToken);

        return ResponseEntity.ok(
                ResponseObjectDTO.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build()
        );
    }

    @Operation(summary = "Create new user ",
            description= "Must be admin role")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registeredUser) throws Exception{

        User user = userService.registerUser(registeredUser);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
