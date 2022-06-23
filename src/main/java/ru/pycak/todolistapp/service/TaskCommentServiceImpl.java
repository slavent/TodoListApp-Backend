package ru.pycak.todolistapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pycak.todolistapp.dao.TaskCommentDAO;
import ru.pycak.todolistapp.dto.CreateTaskCommentDTO;
import ru.pycak.todolistapp.dto.TaskCommentDTO;
import ru.pycak.todolistapp.entity.TaskComment;
import ru.pycak.todolistapp.exception.TaskCommentNotFoundException;
import ru.pycak.todolistapp.utils.MappingUtils;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TaskCommentServiceImpl implements TaskCommentService {

    private final MappingUtils mappingUtils;
    private final TaskCommentDAO taskCommentDAO;

    @Override
    @Transactional
    public void save(TaskCommentDTO comment) {
        taskCommentDAO.save(mappingUtils.convertTaskCommentToEntity(comment));
    }

    @Override
    @Transactional
    public TaskCommentDTO create(
            CreateTaskCommentDTO createTaskCommentDTO,
            Long userId,
            Long taskId
    ) {
        TaskComment comment = new TaskComment();
        comment.setText(createTaskCommentDTO.getText());
        comment.setTaskId(taskId);
        comment.setUserId(userId);

        taskCommentDAO.save(comment);
        return mappingUtils.convertTaskCommentToDto(comment);
    }

    @Override
    @Transactional
    public void remove(Long commentId) {
        taskCommentDAO.remove(commentId);
    }

    @Override
    @Transactional
    public TaskCommentDTO get(Long commentId, Long taskId, Long userId) {
        return taskCommentDAO
                .findByIdAndTaskIdAndUserId(commentId, taskId, userId)
                .map(mappingUtils::convertTaskCommentToDto)
                .orElseThrow(() -> new TaskCommentNotFoundException(
                        "Comment not found for userId="+userId+" and taskId="+taskId
                ));
    }
}
