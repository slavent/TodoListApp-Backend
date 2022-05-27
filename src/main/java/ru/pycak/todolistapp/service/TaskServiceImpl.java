package ru.pycak.todolistapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pycak.todolistapp.dao.TaskDAO;
import ru.pycak.todolistapp.dao.TaskStatusDAO;
import ru.pycak.todolistapp.entity.Task;
import ru.pycak.todolistapp.entity.TaskStatus;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDAO taskDAO;
    private final TaskStatusDAO taskStatusDAO;

    @Autowired
    public TaskServiceImpl(TaskDAO taskDAO, TaskStatusDAO taskStatusDAO) {
        this.taskDAO = taskDAO;
        this.taskStatusDAO = taskStatusDAO;
    }

    @Override
    @Transactional
    public List<Task> getTasksByUser(Long id) {
        return taskDAO.getTasksByUser(id);
    }

    @Override
    @Transactional
    public Task get(Long id) {
        return taskDAO.get(id);
    }

    @Override
    @Transactional
    public void save(Task task) {
        TaskStatus status = taskStatusDAO.get(0);
        task.setStatus(status);
        taskDAO.save(task);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        taskDAO.remove(id);
    }
}
