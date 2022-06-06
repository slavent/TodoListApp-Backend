package ru.pycak.todolistapp.service;

import ru.pycak.todolistapp.entity.TaskComment;

public interface TaskCommentService {

    /**
     * Save a comment or update if one already exists
     *
     * @param comment TaskComment instance
     */
    public void save(TaskComment comment);

    /**
     * Remove comment by its identifier
     *
     * @param id comment identifier
     */
    public void removeComment(Long id);
}
