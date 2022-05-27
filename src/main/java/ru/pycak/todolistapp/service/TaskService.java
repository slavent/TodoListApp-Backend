package ru.pycak.todolistapp.service;

import ru.pycak.todolistapp.entity.Task;

import java.util.List;

public interface TaskService {

    /**
     * @param id User unique identifier
     * @return a list of tasks assigned to the given user
     */
    public List<Task> getTasksByUser(Long id);

    /**
     * @param id Task unique identifier
     * @return an instance of Task with given id
     */
    public Task get(Long id);

    /**
     * Save a new task or update if one already exists
     *
     * @param task an instance of Task
     */
    public void save(Task task);

    /**
     * Remove a task by its identifier
     *
     * @param id Task unique identifier
     */
    public void remove(Long id);
}
