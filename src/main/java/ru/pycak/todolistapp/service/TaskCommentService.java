package ru.pycak.todolistapp.service;

import ru.pycak.todolistapp.dto.CreateTaskCommentDTO;
import ru.pycak.todolistapp.dto.TaskCommentDTO;

public interface TaskCommentService {

    /**
     * Save a comment or update if one already exists
     *
     * @param commentDTO TaskComment instance
     */
    public void save(TaskCommentDTO commentDTO);

    public TaskCommentDTO createComment(CreateTaskCommentDTO createTaskCommentDTO, Long userId, Long taskId);

    /**
     * Remove comment by its identifier
     *
     * @param id comment identifier
     */
    public void removeComment(Long id);

    public TaskCommentDTO findByIdAndTaskIdAndUserId(Long commentId, Long taskId, Long userId);
}
