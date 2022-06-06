package ru.pycak.todolistapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.entity.Task;
import ru.pycak.todolistapp.entity.TaskComment;
import ru.pycak.todolistapp.entity.User;
import ru.pycak.todolistapp.service.TaskCommentService;
import ru.pycak.todolistapp.service.TaskService;
import ru.pycak.todolistapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users/{id}/profile")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUserProfile(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public void changeUserData(@PathVariable Long id, @RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping
    public void removeUser(@PathVariable Long id) {
        userService.removeUser(id);
    }
}
