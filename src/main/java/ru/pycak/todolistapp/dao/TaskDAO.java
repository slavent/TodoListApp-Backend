package ru.pycak.todolistapp.dao;

import ru.pycak.todolistapp.entity.Task;

import java.util.List;

public interface TaskDAO {

    /**
     * Save or update the task if one already exists in database
     *
     * @param task Task instance
     */
    public void save(Task task);

    /**
     * @param id User unique identifier
     * @return list of tasks assigned to given user
     */
    public List<Task> getTasksByUser(Long id);

    /**
     * @param id Task unique identifier
     * @return a task with given id
     */
    public Task get(Long id);

    /**
     * @param taskId task identifier
     * @param userId user identifier
     * @return Task with given conditions or null
     */
    public Task findByIdAndUserId(Long taskId, Long userId);

    /**
     * Remove the task by its identifier
     *
     * @param id Task unique identifier
     */
    public void remove(Long id);
}
