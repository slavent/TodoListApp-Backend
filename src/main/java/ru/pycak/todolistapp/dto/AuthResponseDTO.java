package ru.pycak.todolistapp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class AuthResponseDTO {
    private final String token;
    private final String refreshToken;
}
