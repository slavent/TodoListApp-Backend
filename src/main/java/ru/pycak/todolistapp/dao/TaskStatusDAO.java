package ru.pycak.todolistapp.dao;

import ru.pycak.todolistapp.entity.TaskStatus;

public interface TaskStatusDAO {

    /**
     * @param id status id
     * @return TaskStatus instance
     */
    public TaskStatus get(int id);
}
