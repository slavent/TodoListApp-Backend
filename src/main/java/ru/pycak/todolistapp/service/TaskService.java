package ru.pycak.todolistapp.service;

import ru.pycak.todolistapp.dto.CreateTaskDTO;
import ru.pycak.todolistapp.dto.ShortTaskDTO;
import ru.pycak.todolistapp.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    /**
     * @param id User unique identifier
     * @return a list of tasks assigned to the given user
     */
    public List<ShortTaskDTO> getTasksByUser(Long id);

    /**
     * @param id Task unique identifier
     * @return a TaskDTO with given id
     */
    public TaskDTO get(Long id);

    /**
     * @param taskId Task unique identifier
     * @param userId User unique identifier
     * @return a task DTO by given id from the list of tasks assigned to the given user
     */
    public TaskDTO getTaskByIdAndUser(Long taskId, Long userId);

    /**
     * Create new task
     *
     * @param createTaskDTO New task
     * @param userId User id to assign task to
     * @return TaskDTO of new task
     */
    public TaskDTO createTask(CreateTaskDTO createTaskDTO, Long userId);

    /**
     * Save a new task or update if one already exists
     *
     * @param task an instance of Task
     */
    public TaskDTO update(TaskDTO task);

    /**
     * Remove a task by its identifier
     *
     * @param id Task unique identifier
     */
    public void remove(Long id);
}
