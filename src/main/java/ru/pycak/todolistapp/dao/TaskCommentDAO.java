package ru.pycak.todolistapp.dao;

import ru.pycak.todolistapp.entity.TaskComment;

public interface TaskCommentDAO {

    /**
     * @param comment TaskComment instance
     */
    public void save(TaskComment comment);

    /**
     * @param id comment unique identifier
     * @return a comment with given id
     */
    public TaskComment get(Long id);

    /**
     * @param id comment unique identifier
     */
    public void remove(Long id);
}
