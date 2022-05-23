package ru.pycak.todolistapp.model;

import lombok.Data;

@Data
public class TaskComment {

    private final Long id;
    private final String text;
    private final int taskId;
    private final int userId;
}
