package ru.pycak.todolistapp.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pycak.todolistapp.user.entity.Role;
import ru.pycak.todolistapp.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public final class UserModel {

    private Long id;
    private String name;
    private String password;
    private String email;
    private String avatarUrl;
    private List<String> roles;

    public UserModel(User entity) {
        id = entity.getId();
        name = entity.getName();
        password = entity.getPassword();
        email = entity.getEmail();
        avatarUrl = entity.getAvatarUrl();
        roles = entity.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
}
