package ru.pycak.todolistapp.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.user.dto.ChangeUserDTO;
import ru.pycak.todolistapp.user.dto.UserDTO;
import ru.pycak.todolistapp.user.model.UserModel;
import ru.pycak.todolistapp.user.service.UserService;

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
        UserModel model = userService.get(authentication.getName());
        return new UserDTO(model);
    }

    @PostMapping("/edit")
    public void changeUserData(
            Authentication authentication,
            @RequestBody ChangeUserDTO changeUserDTO
    ) {
        Long userId = userService.getId(authentication.getName());
        UserModel model = new UserModel();
        model.setId(userId);
        model.setName(changeUserDTO.getName());
        userService.update(model);
    }

    @DeleteMapping
    public void removeUser(Authentication authentication) {
        Long id = userService.getId(authentication.getName());
        userService.remove(id);
    }
}
