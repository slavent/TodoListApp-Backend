package ru.pycak.todolistapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.pycak.todolistapp.entity.TaskComment;

import java.util.Date;

@Data
public final class TaskCommentModel {

    private Long id;
    private Long taskId;
    private Long userId;
    private String text;
    private Date creationDate;
    private Date editDate;

    public TaskCommentModel(TaskComment entity) {
        id = entity.getId();
        taskId = entity.getTask().getId();
        userId = entity.getUser().getId();
        text = entity.getText();
        creationDate = entity.getCreationDate();
        editDate = entity.getEditDate();
    }
}
