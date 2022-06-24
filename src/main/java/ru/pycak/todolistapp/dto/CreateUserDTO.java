package ru.pycak.todolistapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public final class CreateUserDTO {

    @NotBlank(message = "Name must not be empty")
    private final String name;

    @Email(message = "Email must be valid")
    private final String email;

    @Size(min = 8, message = "Password must be at least 8 characters length")
    private final String password;
}
