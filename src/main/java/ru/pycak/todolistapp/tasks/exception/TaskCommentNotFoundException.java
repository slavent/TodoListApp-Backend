package ru.pycak.todolistapp.tasks.exception;

public class TaskCommentNotFoundException extends RuntimeException {

    public TaskCommentNotFoundException(String message) {
        super(message);
    }
}
