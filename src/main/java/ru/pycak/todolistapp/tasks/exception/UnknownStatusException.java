package ru.pycak.todolistapp.tasks.exception;

public class UnknownStatusException extends RuntimeException {

    public UnknownStatusException(String message) {
        super(message);
    }
}
