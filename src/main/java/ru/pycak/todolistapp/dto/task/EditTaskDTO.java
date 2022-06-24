package ru.pycak.todolistapp.dto.task;

import lombok.Data;

@Data
public final class EditTaskDTO {

    private final Long userId;
    private final Integer statusId;
    private final String title;
    private final String description;
}
