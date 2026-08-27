package com.example.alkananda.controller;

import com.example.alkananda.entity.User;
import com.example.alkananda.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

    @GetMapping("/admin-test")
    public String adminTest() {
        return "Welcome Admin";
    }
}