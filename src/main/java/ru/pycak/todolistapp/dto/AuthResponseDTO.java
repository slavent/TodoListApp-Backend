package ru.pycak.todolistapp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthResponseDTO {
    private final String token;
    private final String refreshToken;
}
