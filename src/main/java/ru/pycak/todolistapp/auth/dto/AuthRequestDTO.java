package ru.pycak.todolistapp.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class AuthRequestDTO {
    private final String login;
    private final String password;
}
