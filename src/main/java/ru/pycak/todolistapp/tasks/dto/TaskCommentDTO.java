package ru.pycak.todolistapp.tasks.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.pycak.todolistapp.tasks.model.TaskCommentModel;

import java.util.Date;

@Data
@RequiredArgsConstructor
public final class TaskCommentDTO {

    private final Long id;
    private final Long taskId;
    private final String text;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final Date creationDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final Date editDate;

    public TaskCommentDTO(TaskCommentModel model) {
        id = model.getId();
        taskId = model.getTaskId();
        text = model.getText();
        creationDate = model.getCreationDate();
        editDate = model.getEditDate();
    }
}
