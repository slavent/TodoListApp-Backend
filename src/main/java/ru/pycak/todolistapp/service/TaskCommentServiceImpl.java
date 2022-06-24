package ru.pycak.todolistapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pycak.todolistapp.dao.TaskCommentDAO;
import ru.pycak.todolistapp.dao.TaskDAO;
import ru.pycak.todolistapp.dao.UserDAO;
import ru.pycak.todolistapp.dto.CreateTaskCommentDTO;
import ru.pycak.todolistapp.entity.Task;
import ru.pycak.todolistapp.entity.TaskComment;
import ru.pycak.todolistapp.entity.User;
import ru.pycak.todolistapp.exception.TaskCommentNotFoundException;
import ru.pycak.todolistapp.exception.TaskDoesNotExistException;
import ru.pycak.todolistapp.exception.UserDoesNotExistException;
import ru.pycak.todolistapp.model.TaskCommentModel;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TaskCommentServiceImpl implements TaskCommentService {

    private final UserDAO userDAO;
    private final TaskDAO taskDAO;
    private final TaskCommentDAO taskCommentDAO;

    @Override
    @Transactional
    public TaskCommentModel get(Long commentId, Long taskId, Long userId) {
        return taskCommentDAO
                .findByIdAndTaskIdAndUserId(commentId, taskId, userId)
                .map(TaskCommentModel::new)
                .orElseThrow(() -> new TaskCommentNotFoundException(
                        "Comment not found for userId="+userId+" and taskId="+taskId
                ));
    }

    @Override
    @Transactional
    public TaskCommentModel create(
            CreateTaskCommentDTO createTaskCommentDTO,
            Long userId,
            Long taskId
    ) {
        User user = userDAO.get(userId);
        if (user == null) {
            throw new UserDoesNotExistException("User with id '"+userId+"' does not exist");
        }

        Task task = taskDAO.get(taskId);
        if (task == null) {
            throw new TaskDoesNotExistException("Task with id '"+taskId+"' does not exist");
        }

        TaskComment comment = new TaskComment();
        comment.setText(createTaskCommentDTO.getText());
        comment.setTask(task);
        comment.setUser(user);

        return new TaskCommentModel(taskCommentDAO.save(comment));
    }

    @Override
    @Transactional
    public void update(TaskCommentModel commentModel) {
        Long commentId = commentModel.getId();
        TaskComment comment = taskCommentDAO.get(commentId);
        if (comment == null) {
            throw new TaskCommentNotFoundException("Comment with id '"+commentId+"' does not exist");
        }

        if (commentModel.getText() != null) {
            comment.setText(commentModel.getText());
        }
        taskCommentDAO.save(comment);
    }

    @Override
    @Transactional
    public void remove(Long commentId) {
        taskCommentDAO.remove(commentId);
    }
}
