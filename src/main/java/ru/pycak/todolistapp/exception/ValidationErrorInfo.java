package ru.pycak.todolistapp.exception;

import lombok.Data;

@Data
public final class ValidationErrorInfo {

    private final String parameter;
    private final String message;
}
