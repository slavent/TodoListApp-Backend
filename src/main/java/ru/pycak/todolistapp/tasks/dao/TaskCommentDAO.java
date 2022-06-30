package ru.pycak.todolistapp.tasks.dao;

import ru.pycak.todolistapp.tasks.entity.TaskComment;

import java.util.Optional;

public interface TaskCommentDAO {

    /**
     * @param comment TaskComment instance
     * @return saved TaskComment instance
     */
    public TaskComment save(TaskComment comment);

    /**
     * @param id comment unique identifier
     * @return a comment with given id
     */
    public TaskComment get(Long id);

    /**
     * @param commentId comment identifier
     * @param taskId task identifier
     * @param userId user identifier
     * @return TaskComment with given conditions or null
     */
    public Optional<TaskComment> findByIdAndTaskIdAndUserId(Long commentId, Long taskId, Long userId);

    /**
     * @param id comment unique identifier
     */
    public void remove(Long id);
}
