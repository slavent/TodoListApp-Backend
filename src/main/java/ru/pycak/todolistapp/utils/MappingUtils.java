package ru.pycak.todolistapp.utils;

import org.springframework.stereotype.Component;
import ru.pycak.todolistapp.dto.ShortTaskDTO;
import ru.pycak.todolistapp.dto.TaskCommentDTO;
import ru.pycak.todolistapp.dto.TaskDTO;
import ru.pycak.todolistapp.entity.Task;
import ru.pycak.todolistapp.entity.TaskComment;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MappingUtils {

    public TaskDTO convertTaskToDto(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getUserId(),
                task.getStatus().getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreationDate(),
                task.getEditDate(),
                convertTaskCommentsToDto(task.getComments())
        );
    }

    public ShortTaskDTO convertTaskToShortDto(Task task) {
        return new ShortTaskDTO(
                task.getId(),
                task.getUserId(),
                task.getStatus().getId(),
                task.getTitle()
        );
    }

    public List<TaskCommentDTO> convertTaskCommentsToDto(List<TaskComment> comments) {
        if (comments == null) {
            return null;
        }
        return comments.stream()
                .map(this::convertTaskCommentToDto)
                .collect(Collectors.toList());
    }

    public TaskCommentDTO convertTaskCommentToDto(TaskComment taskComment) {
        return new TaskCommentDTO(
                taskComment.getId(),
                taskComment.getUserId(),
                taskComment.getTaskId(),
                taskComment.getText(),
                taskComment.getCreationDate(),
                taskComment.getEditDate()
        );
    }

    public TaskComment convertTaskCommentToEntity(TaskCommentDTO commentDTO) {
        return new TaskComment(
                commentDTO.getId(),
                commentDTO.getText(),
                commentDTO.getCreationDate(),
                commentDTO.getEditDate(),
                commentDTO.getTaskId(),
                commentDTO.getUserId()
        );
    }
}
