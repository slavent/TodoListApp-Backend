package ru.pycak.todolistapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class TaskDTO {

    private final Long id;
    private final Long userId;
    private final Integer statusId;
    private final String title;
    private final String description;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final Date creationDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final Date editDate;

    private final List<TaskCommentDTO> comments;
}
