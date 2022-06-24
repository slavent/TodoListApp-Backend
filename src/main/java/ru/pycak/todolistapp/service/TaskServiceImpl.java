package ru.pycak.todolistapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pycak.todolistapp.dao.TaskCommentDAO;
import ru.pycak.todolistapp.dao.TaskDAO;
import ru.pycak.todolistapp.dao.TaskStatusDAO;
import ru.pycak.todolistapp.dao.UserDAO;
import ru.pycak.todolistapp.dto.task.CreateTaskDTO;
import ru.pycak.todolistapp.entity.Task;
import ru.pycak.todolistapp.entity.TaskStatus;
import ru.pycak.todolistapp.entity.User;
import ru.pycak.todolistapp.exception.TaskDoesNotExistException;
import ru.pycak.todolistapp.exception.UnknownStatusException;
import ru.pycak.todolistapp.exception.UserDoesNotExistException;
import ru.pycak.todolistapp.model.TaskModel;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskDAO taskDAO;
    private final TaskStatusDAO taskStatusDAO;
    private final UserDAO userDAO;
    private final TaskCommentDAO taskCommentDAO;

    @Override
    @Transactional
    public TaskModel get(Long id) {
        return new TaskModel(taskDAO.get(id));
    }

    @Override
    @Transactional
    public List<TaskModel> find(Long userId) {
        return taskDAO
                .getTasksByUser(userId)
                .stream()
                .map(TaskModel::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskModel find(Long taskId, Long userId) {
        return taskDAO
                .findByIdAndUserId(taskId, userId)
                .map(TaskModel::new)
                .orElseThrow(() -> new TaskDoesNotExistException(
                        "Task not found by userId="+userId+" and taskId="+taskId
                ));
    }

    @Override
    @Transactional
    public TaskModel create(CreateTaskDTO createTaskDTO, Long userId) {
        int statusId = createTaskDTO.getStatusId();

        TaskStatus status = taskStatusDAO.get(statusId);
        if (status == null) {
            throw new UnknownStatusException("There is no status with id=" + statusId);
        }

        User user = userDAO.get(userId);
        if (user == null) {
            throw new UserDoesNotExistException("There is no user with id=" + userId);
        }

        Task task = new Task();
        task.setStatus(status);
        task.setUser(user);
        task.setTitle(createTaskDTO.getTitle());
        task.setDescription(createTaskDTO.getDescription());

        return new TaskModel(taskDAO.save(task));
    }

    @Override
    @Transactional
    public void update(TaskModel taskModel) {
        Task task = taskDAO.get(taskModel.getId());
        if (task == null) {
            throw new TaskDoesNotExistException("Task with id '"+taskModel.getId()+"' does not exist.");
        }

        if (taskModel.getStatusId() != null) {
            TaskStatus status = taskStatusDAO.get(taskModel.getStatusId());
            task.setStatus(status);
        }
        if (taskModel.getUserId() != null) {
            User user = userDAO.get(task.getUser().getId());
            task.setUser(user);
        }
        if (taskModel.getTitle() != null) {
            task.setTitle(taskModel.getTitle());
        }
        if (taskModel.getDescription() != null) {
            task.setDescription(taskModel.getDescription());
        }

        taskDAO.save(task);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        taskDAO.remove(id);
    }
}
