package ru.pycak.todolistapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.dto.UserDTO;
import ru.pycak.todolistapp.service.UserService;

@RestController
@RequestMapping("/api/users/{id}/profile")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDTO getUserProfile(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public void changeUserData(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userService.update(userDTO);
    }

    @DeleteMapping
    public void removeUser(@PathVariable Long id) {
        userService.removeUser(id);
    }
}
