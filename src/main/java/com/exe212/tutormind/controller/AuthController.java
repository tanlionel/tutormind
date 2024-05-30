package com.exe212.tutormind.controller;

import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.model.DTO.LoginRequestDTO;
import com.exe212.tutormind.model.DTO.RegisterRequestDTO;
import com.exe212.tutormind.model.DTO.ResponseObjectDTO;
import com.exe212.tutormind.service.service_interface.JwtService;
import com.exe212.tutormind.service.service_interface.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    @Autowired
    private final UserService userService;
    @Autowired
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
