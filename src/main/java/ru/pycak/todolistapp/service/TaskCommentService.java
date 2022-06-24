package ru.pycak.todolistapp.service;

import ru.pycak.todolistapp.dto.CreateTaskCommentDTO;
import ru.pycak.todolistapp.model.TaskCommentModel;

public interface TaskCommentService {

    /**
     * Get comment which was left to the given task by given user.
     *
     * @param commentId comment identifier
     * @param taskId task identifier
     * @param userId user identifier
     * @return TaskComment instance
     */
    public TaskCommentModel get(Long commentId, Long taskId, Long userId);

    /**
     * Add a comment to a task.
     *
     * @param createTaskCommentDTO a DTO with comment info
     * @param userId User identifier who creates comment
     * @param taskId Task identifier which the comment was left to
     * @return created comment instance
     */
    public TaskCommentModel create(CreateTaskCommentDTO createTaskCommentDTO, Long userId, Long taskId);

    /**
     * Save a comment or update if one already exists.
     *
     * @param commentModel TaskComment instance
     */
    public void update(TaskCommentModel commentModel);

    /**
     * Remove the comment which was left to the given task by given user.
     *
     * @param commentId comment identifier
     */
    public void remove(Long commentId);
}
