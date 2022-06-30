package ru.pycak.todolistapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.dto.UserDTO;
import ru.pycak.todolistapp.exception.IllegalOperationException;
import ru.pycak.todolistapp.service.UserService;

@RestController
@RequestMapping("/api/profile")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDTO getUserProfile(Authentication authentication) {
        return userService.get(authentication.getName());
    }

    @PostMapping
    public void changeUserData(Authentication authentication, @RequestBody UserDTO userDTO) {
        UserDTO user = userService.get(authentication.getName());
        if (!user.getId().equals(userDTO.getId())) {
            throw new IllegalOperationException("User cannot change the data of other users");
        }
        userService.update(userDTO);
    }

    @DeleteMapping
    public void removeUser(Authentication authentication) {
        Long id = userService.getId(authentication.getName());
        userService.remove(id);
    }
}
