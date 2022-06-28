package ru.pycak.todolistapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pycak.todolistapp.dao.TaskCommentDAO;
import ru.pycak.todolistapp.dto.CreateTaskCommentDTO;
import ru.pycak.todolistapp.dto.TaskCommentDTO;
import ru.pycak.todolistapp.entity.TaskComment;
import ru.pycak.todolistapp.utils.MappingUtils;

import javax.transaction.Transactional;

@Service
public class TaskCommentServiceImpl implements TaskCommentService {

    private final MappingUtils mappingUtils;
    private final TaskCommentDAO taskCommentDAO;

    @Autowired
    public TaskCommentServiceImpl(
            MappingUtils mappingUtils,
            TaskCommentDAO taskCommentDAO
    ) {
        this.mappingUtils = mappingUtils;
        this.taskCommentDAO = taskCommentDAO;
    }

    @Override
    @Transactional
    public void save(TaskCommentDTO comment) {
        taskCommentDAO.save(mappingUtils.convertTaskCommentToEntity(comment));
    }

    @Override
    @Transactional
    public TaskCommentDTO createComment(
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
    public void removeComment(Long id) {
        taskCommentDAO.remove(id);
    }

    @Override
    @Transactional
    public TaskCommentDTO findByIdAndTaskIdAndUserId(Long commentId, Long taskId, Long userId) {
        TaskComment comment = taskCommentDAO
                .findByIdAndTaskIdAndUserId(commentId, taskId, userId);

        return mappingUtils.convertTaskCommentToDto(comment);
    }
}
