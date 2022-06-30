package ru.pycak.todolistapp.exception;

public class UnknownStatusException extends RuntimeException {

    public UnknownStatusException(String message) {
        super(message);
    }
}
