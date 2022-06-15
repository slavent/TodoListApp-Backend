package ru.pycak.todolistapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {

    private final Long id;
    private final String name;
    private final String email;
    private final String avatarUrl;
}
