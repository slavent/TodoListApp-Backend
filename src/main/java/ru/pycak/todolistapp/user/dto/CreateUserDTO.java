package ru.pycak.todolistapp.user.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public final class CreateUserDTO {

    @NotNull
    @NotBlank
    private final String name;

    @NotNull
    @Email
    private final String email;

    @NotNull
    @Size(min = 8, message = "длина должна быть не менее 8 символов")
    private final String password;
}
