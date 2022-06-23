package ru.pycak.todolistapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pycak.todolistapp.dao.TaskDAO;
import ru.pycak.todolistapp.dao.TaskStatusDAO;
import ru.pycak.todolistapp.dao.UserDAO;
import ru.pycak.todolistapp.dto.CreateTaskDTO;
import ru.pycak.todolistapp.dto.ShortTaskDTO;
import ru.pycak.todolistapp.dto.TaskDTO;
import ru.pycak.todolistapp.entity.Task;
import ru.pycak.todolistapp.entity.TaskStatus;
import ru.pycak.todolistapp.entity.User;
import ru.pycak.todolistapp.exception.TaskDoesNotExistException;
import ru.pycak.todolistapp.utils.MappingUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskDAO taskDAO;
    private final TaskStatusDAO taskStatusDAO;
    private final UserDAO userDAO;
    private final MappingUtils mappingUtils;

    @Override
    @Transactional
    public TaskDTO get(Long id) {
        return mappingUtils.convertTaskToDto(taskDAO.get(id));
    }

    @Override
    @Transactional
    public List<ShortTaskDTO> find(Long userId) {
        return taskDAO.getTasksByUser(userId).stream()
                .map(mappingUtils::convertTaskToShortDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskDTO find(Long taskId, Long userId) {
        return taskDAO
                .findByIdAndUserId(taskId, userId)
                .map(mappingUtils::convertTaskToDto)
                .orElseThrow(() -> new TaskDoesNotExistException(
                        "Task not found by userId="+userId+" and taskId="+taskId
                ));
    }

    @Override
    @Transactional
    public TaskDTO create(CreateTaskDTO createTaskDTO, Long userId) {
        Task task = new Task();
        TaskStatus status = taskStatusDAO.get(createTaskDTO.getStatusId());
        User user = userDAO.get(userId);
        task.setStatus(status);
        task.setUser(user);
        task.setTitle(createTaskDTO.getTitle());
        task.setDescription(createTaskDTO.getDescription());

        taskDAO.save(task);
        return mappingUtils.convertTaskToDto(task);
    }

    @Override
    @Transactional
    public TaskDTO update(TaskDTO taskDTO) {
        Task task = taskDAO.get(taskDTO.getId());
        if (task == null) {
            throw new TaskDoesNotExistException("Task with id '"+taskDTO.getId()+"' does not exist.");
        }

        if (taskDTO.getStatusId() != null) {
            TaskStatus status = taskStatusDAO.get(taskDTO.getStatusId());
            task.setStatus(status);
        }
        if (taskDTO.getUserId() != null) {
            User user = userDAO.get(task.getUserId());
            task.setUser(user);
        }
        if (taskDTO.getTitle() != null) {
            task.setTitle(taskDTO.getTitle());
        }
        if (taskDTO.getDescription() != null) {
            task.setDescription(taskDTO.getDescription());
        }

        taskDAO.save(task);
        return mappingUtils.convertTaskToDto(task);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        taskDAO.remove(id);
    }
}
