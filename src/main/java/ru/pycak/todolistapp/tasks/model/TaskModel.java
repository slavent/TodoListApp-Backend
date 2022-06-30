package ru.pycak.todolistapp.tasks.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pycak.todolistapp.tasks.entity.Task;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public final class TaskModel {

    private Long id;
    private Long userId;
    private Integer statusId;
    private String title;
    private String description;
    private Date creationDate;
    private Date editDate;
    private List<TaskCommentModel> comments;

    public TaskModel(Task entity) {
        id = entity.getId();
        userId = entity.getUser().getId();
        statusId = entity.getStatus().getId();
        title = entity.getTitle();
        description = entity.getDescription();
        creationDate = entity.getCreationDate();
        editDate = entity.getEditDate();
        comments = Optional.ofNullable(entity.getComments())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(TaskCommentModel::new)
                .collect(Collectors.toList());
    }
}
