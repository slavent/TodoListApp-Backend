package ru.pycak.todolistapp.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.response.BaseResponse;
import ru.pycak.todolistapp.response.SuccessResponseFactory;
import ru.pycak.todolistapp.user.dto.ChangeUserDTO;
import ru.pycak.todolistapp.user.dto.UserDTO;
import ru.pycak.todolistapp.user.model.UserModel;
import ru.pycak.todolistapp.user.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SuccessResponseFactory responseFactory;

    @GetMapping
    public ResponseEntity<BaseResponse<UserDTO>> getUserProfile(
            Authentication authentication
    ) {
        UserModel model = userService.get(authentication.getName());
        return responseFactory.makeSuccessResponse(new UserDTO(model));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> changeUserData(
            Authentication authentication,
            @RequestBody @Valid ChangeUserDTO changeUserDTO
    ) {
        Long userId = userService.getId(authentication.getName());
        UserModel model = new UserModel();
        model.setId(userId);
        model.setName(changeUserDTO.getName());
        userService.update(model);
        return responseFactory.makeSuccessResponse();
    }

    @DeleteMapping
    public ResponseEntity<?> removeUser(Authentication authentication) {
        Long id = userService.getId(authentication.getName());
        userService.remove(id);
        return responseFactory.makeSuccessResponse();
    }
}
