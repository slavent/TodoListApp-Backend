package ru.pycak.todolistapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pycak.todolistapp.entity.User;
import ru.pycak.todolistapp.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        userService.create(user);
        return user;
    }
}
