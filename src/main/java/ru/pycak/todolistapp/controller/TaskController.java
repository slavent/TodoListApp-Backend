package ru.pycak.todolistapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.entity.Task;
import ru.pycak.todolistapp.entity.TaskComment;
import ru.pycak.todolistapp.entity.User;
import ru.pycak.todolistapp.service.TaskCommentService;
import ru.pycak.todolistapp.service.TaskService;
import ru.pycak.todolistapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/tasks")
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
    public Task createTask(@PathVariable Long userId, @RequestBody Task task) {
        User user = userService.getUser(userId);
        task.setUser(user);
        taskService.save(task);
        return task;
    }

    @GetMapping
    public List<Task> getUserTasks(@PathVariable Long userId) {
        return taskService.getTasksByUser(userId);
    }

    @GetMapping("/{taskId}")
    public Task getUserTaskById(@PathVariable @NonNull Long userId, @PathVariable @NonNull Long taskId) {
        return taskService
                .getTasksByUser(userId)
                .stream()
                .filter(t -> t.getId().equals(taskId))
                .findAny()
                .orElse(null);
    }

    // Comments

    @PostMapping("/{taskId}/comments")
    public TaskComment createComment(
            @PathVariable Long userId,
            @PathVariable Long taskId,
            @RequestBody TaskComment comment
    ) {
        Task task = taskService.get(taskId);
        task.getComments().add(comment);
        comment.setTask(task);
        comment.setUser(task.getUser());
        taskService.save(task);
        return comment;
    }

    @GetMapping("/{taskId}/comments/{commentId}")
    public TaskComment getComment(
            @PathVariable Long userId,
            @PathVariable Long taskId,
            @PathVariable Long commentId
    ) {
        return taskService.get(taskId)
                .getComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findAny()
                .orElse(null);
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
