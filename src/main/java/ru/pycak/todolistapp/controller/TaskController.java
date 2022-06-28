package ru.pycak.todolistapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.dto.*;
import ru.pycak.todolistapp.service.TaskCommentService;
import ru.pycak.todolistapp.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskCommentService taskCommentService;

    @Autowired
    public TaskController(
            TaskService taskService,
            TaskCommentService taskCommentService
    ) {
        this.taskService = taskService;
        this.taskCommentService = taskCommentService;
    }

    // Tasks

    @PostMapping
    public TaskDTO createTask(@PathVariable Long userId, @RequestBody CreateTaskDTO task) {
        return taskService.createTask(task, userId);
    }

    @GetMapping
    public List<ShortTaskDTO> getUserTasks(@PathVariable Long userId) {
        return taskService.getTasksByUser(userId);
    }

    @GetMapping("/{taskId}")
    public TaskDTO getUserTaskById(
            @PathVariable @NonNull Long userId,
            @PathVariable @NonNull Long taskId
    ) {
        return taskService.getTaskByIdAndUser(taskId, userId);
    }

    // Comments

    @PostMapping("/{taskId}/comments")
    public TaskCommentDTO createComment(
            @PathVariable Long userId,
            @PathVariable Long taskId,
            @RequestBody CreateTaskCommentDTO commentDTO
    ) {
        return taskCommentService.createComment(commentDTO, userId, taskId);
    }

    @GetMapping("/{taskId}/comments/{commentId}")
    public TaskCommentDTO getComment(
            @PathVariable Long userId,
            @PathVariable Long taskId,
            @PathVariable Long commentId
    ) {
        return taskCommentService.findByIdAndTaskIdAndUserId(commentId, taskId, userId);
    }

    @DeleteMapping("/{taskId}/comments/{commentId}")
    public void removeComment(
            @PathVariable String userId,
            @PathVariable String taskId,
            @PathVariable Long commentId
    ) {
        taskCommentService.removeComment(commentId);
    }
}
