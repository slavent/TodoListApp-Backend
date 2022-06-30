package ru.pycak.todolistapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.dto.*;
import ru.pycak.todolistapp.service.TaskCommentService;
import ru.pycak.todolistapp.service.TaskService;
import ru.pycak.todolistapp.service.UserService;

import javax.validation.constraints.NotNull;
import java.util.List;

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
        return taskService.create(task, userId);
    }

    @GetMapping
    public List<ShortTaskDTO> getUserTasks(Authentication authentication) {
        Long userId = userService.getId(authentication.getName());
        return taskService.find(userId);
    }

    @GetMapping("/{taskId}")
    public TaskDTO getUserTaskById(
            Authentication authentication,
            @PathVariable @NonNull Long taskId
    ) {
        Long userId = userService.getId(authentication.getName());
        return taskService.find(taskId, userId);
    }

    @PostMapping("/{taskId}")
    public void updateTask(
            Authentication authentication,
            @PathVariable @NonNull Long taskId,
            @RequestBody TaskDTO taskDTO
    ) {
        Long userId = userService.getId(authentication.getName());
        taskService.find(taskId, userId);
        taskService.update(taskDTO);
    }

    @DeleteMapping("/{taskId}")
    public void removeTask(
            Authentication authentication,
            @PathVariable @NotNull Long taskId
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskDTO task = taskService.find(taskId, userId);
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
        return taskCommentService.create(commentDTO, userId, taskId);
    }

    @GetMapping("/{taskId}/comments/{commentId}")
    public TaskCommentDTO getComment(
            Authentication authentication,
            @PathVariable Long taskId,
            @PathVariable Long commentId
    ) {
        Long userId = userService.getId(authentication.getName());
        return taskCommentService.get(commentId, taskId, userId);
    }

    @DeleteMapping("/{taskId}/comments/{commentId}")
    public void removeComment(
            Authentication authentication,
            @PathVariable Long taskId,
            @PathVariable Long commentId
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskCommentDTO commentDTO = taskCommentService.get(commentId, taskId, userId);
        taskCommentService.remove(commentDTO.getId());
    }
}
