package ru.pycak.todolistapp.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public final class AuthRequestDTO {

    @NotNull @NotBlank
    private final String login;

    @NotNull @NotBlank
    private final String password;
}
