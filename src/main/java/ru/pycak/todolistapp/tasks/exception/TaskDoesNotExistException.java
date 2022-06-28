package ru.pycak.todolistapp.tasks.exception;

public class TaskDoesNotExistException extends RuntimeException {

    public TaskDoesNotExistException(String message) {
        super(message);
    }
}
