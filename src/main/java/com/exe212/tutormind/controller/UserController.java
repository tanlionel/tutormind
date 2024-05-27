package com.exe212.tutormind.controller;

import com.exe212.tutormind.service.service_interface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
