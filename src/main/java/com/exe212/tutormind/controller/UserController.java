package com.exe212.tutormind.controller;

import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.exception.InvalidateException;
import com.exe212.tutormind.exception.UserDoesNotExistException;
import com.exe212.tutormind.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/secure")
    public String getFromSecure(){
        return "hello secure";
    }
}
