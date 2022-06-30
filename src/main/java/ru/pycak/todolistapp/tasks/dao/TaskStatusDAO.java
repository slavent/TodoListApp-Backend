package ru.pycak.todolistapp.tasks.dao;

import ru.pycak.todolistapp.tasks.entity.TaskStatus;

public interface TaskStatusDAO {

    /**
     * @param id status id
     * @return TaskStatus instance
     */
    public TaskStatus get(int id);
}
