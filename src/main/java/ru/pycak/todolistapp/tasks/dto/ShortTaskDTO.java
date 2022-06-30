package ru.pycak.todolistapp.tasks.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.pycak.todolistapp.tasks.model.TaskModel;

@Data
@RequiredArgsConstructor
public final class ShortTaskDTO {

    private final Long id;
    private final Long userId;
    private final Integer statusId;
    private final String title;

    public ShortTaskDTO(TaskModel model) {
        id = model.getId();
        userId = model.getUserId();
        statusId = model.getStatusId();
        title = model.getTitle();
    }
}
