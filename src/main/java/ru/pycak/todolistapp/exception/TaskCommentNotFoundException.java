package ru.pycak.todolistapp.exception;

public class TaskCommentNotFoundException extends RuntimeException {

    public TaskCommentNotFoundException(String message) {
        super(message);
    }
}
