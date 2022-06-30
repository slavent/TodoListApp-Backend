package ru.pycak.todolistapp.exception;

public class TaskDoesNotExistException extends RuntimeException {

    public TaskDoesNotExistException(String message) {
        super(message);
    }
}
