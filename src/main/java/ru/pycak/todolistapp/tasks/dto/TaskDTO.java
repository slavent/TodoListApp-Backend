package ru.pycak.todolistapp.tasks.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.pycak.todolistapp.tasks.model.TaskModel;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public final class TaskDTO {

    private final Long id;
    private final Integer statusId;
    private final String title;
    private final String description;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final Date creationDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final Date editDate;

    private final List<TaskCommentDTO> comments;

    public TaskDTO(TaskModel model) {
        id = model.getId();
        statusId = model.getStatusId();
        title = model.getTitle();
        description = model.getDescription();
        creationDate = model.getCreationDate();
        editDate = model.getEditDate();
        comments = Optional.ofNullable(model.getComments())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(TaskCommentDTO::new)
                .collect(Collectors.toList());
    }
}
