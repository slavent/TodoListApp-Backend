package ru.pycak.todolistapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.pycak.todolistapp.model.UserModel;

@Data
@RequiredArgsConstructor
public final class UserDTO {

    private final Long id;
    private final String name;
    private final String email;
    private final String avatarUrl;

    public UserDTO(UserModel model) {
        id = model.getId();
        name = model.getName();
        email = model.getEmail();
        avatarUrl = model.getAvatarUrl();
    }

    public UserModel toModel() {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setName(name);
        userModel.setEmail(email);
        userModel.setAvatarUrl(avatarUrl);
        return userModel;
    }
}
