package ru.pycak.todolistapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.dto.*;
import ru.pycak.todolistapp.dto.task.CreateTaskDTO;
import ru.pycak.todolistapp.dto.task.EditTaskDTO;
import ru.pycak.todolistapp.dto.task.ShortTaskDTO;
import ru.pycak.todolistapp.dto.task.TaskDTO;
import ru.pycak.todolistapp.model.TaskCommentModel;
import ru.pycak.todolistapp.model.TaskModel;
import ru.pycak.todolistapp.service.TaskCommentService;
import ru.pycak.todolistapp.service.TaskService;
import ru.pycak.todolistapp.service.UserService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final UserService userService;
    private final TaskService taskService;
    private final TaskCommentService taskCommentService;

    @Autowired
    public TaskController(
            UserService userService,
            TaskService taskService,
            TaskCommentService taskCommentService
    ) {
        this.userService = userService;
        this.taskService = taskService;
        this.taskCommentService = taskCommentService;
    }

    // Tasks

    @PostMapping
    public TaskDTO createTask(Authentication authentication, @RequestBody CreateTaskDTO task) {
        Long userId = userService.getId(authentication.getName());
        TaskModel model = taskService.create(task, userId);
        return new TaskDTO(model);
    }

    @GetMapping
    public List<ShortTaskDTO> getUserTasks(Authentication authentication) {
        Long userId = userService.getId(authentication.getName());
        return taskService.find(userId)
                .stream()
                .map(ShortTaskDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{taskId}")
    public TaskDTO getUserTaskById(
            Authentication authentication,
            @PathVariable @NonNull Long taskId
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskModel model = taskService.find(taskId, userId);
        return new TaskDTO(model);
    }

    @PostMapping("/{taskId}")
    public void updateTask(
            Authentication authentication,
            @PathVariable @NonNull Long taskId,
            @RequestBody EditTaskDTO taskDTO
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskModel model = taskService.find(taskId, userId);

        model.setUserId(taskDTO.getUserId());
        model.setStatusId(taskDTO.getStatusId());
        model.setTitle(taskDTO.getTitle());
        model.setDescription(taskDTO.getDescription());

        taskService.update(model);
    }

    @DeleteMapping("/{taskId}")
    public void removeTask(
            Authentication authentication,
            @PathVariable @NotNull Long taskId
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskModel task = taskService.find(taskId, userId);
        taskService.remove(task.getId());
    }

    // Comments

    @PostMapping("/{taskId}/comments")
    public TaskCommentDTO createComment(
            Authentication authentication,
            @PathVariable Long taskId,
            @RequestBody CreateTaskCommentDTO commentDTO
    ) {
        Long userId = userService.getId(authentication.getName());
        return new TaskCommentDTO(taskCommentService.create(commentDTO, userId, taskId));
    }

    @GetMapping("/{taskId}/comments/{commentId}")
    public TaskCommentDTO getComment(
            Authentication authentication,
            @PathVariable Long taskId,
            @PathVariable Long commentId
    ) {
        Long userId = userService.getId(authentication.getName());
        return new TaskCommentDTO(taskCommentService.get(commentId, taskId, userId));
    }

    @DeleteMapping("/{taskId}/comments/{commentId}")
    public void removeComment(
            Authentication authentication,
            @PathVariable Long taskId,
            @PathVariable Long commentId
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskCommentModel commentModel = taskCommentService.get(commentId, taskId, userId);
        taskCommentService.remove(commentModel.getId());
    }
}
