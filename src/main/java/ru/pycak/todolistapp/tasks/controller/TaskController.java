package ru.pycak.todolistapp.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.response.BaseResponse;
import ru.pycak.todolistapp.response.SuccessResponseFactory;
import ru.pycak.todolistapp.tasks.dto.*;
import ru.pycak.todolistapp.tasks.model.TaskCommentModel;
import ru.pycak.todolistapp.tasks.model.TaskModel;
import ru.pycak.todolistapp.tasks.service.TaskCommentService;
import ru.pycak.todolistapp.tasks.service.TaskService;
import ru.pycak.todolistapp.user.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final UserService userService;
    private final TaskService taskService;
    private final TaskCommentService taskCommentService;
    private final SuccessResponseFactory responseFactory;

    // Tasks

    @PostMapping
    public ResponseEntity<BaseResponse<TaskDTO>> createTask(
            Authentication authentication,
            @RequestBody @Valid CreateTaskDTO task
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskModel model = taskService.create(task, userId);
        return responseFactory.makeSuccessResponse(new TaskDTO(model));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ShortTaskDTO>>> getUserTasks(
            Authentication authentication
    ) {
        Long userId = userService.getId(authentication.getName());
        List<ShortTaskDTO> tasks = taskService.find(userId)
                .stream()
                .map(ShortTaskDTO::new)
                .collect(Collectors.toList());

        return responseFactory.makeSuccessResponse(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<BaseResponse<TaskDTO>> getUserTaskById(
            Authentication authentication,
            @PathVariable @NonNull Long taskId
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskModel model = taskService.find(taskId, userId);
        TaskDTO taskDTO = new TaskDTO(model);
        return responseFactory.makeSuccessResponse(taskDTO);
    }

    @PostMapping("/{taskId}")
    public ResponseEntity<?> updateTask(
            Authentication authentication,
            @PathVariable @NonNull Long taskId,
            @RequestBody EditTaskDTO taskDTO
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskModel model = taskService.find(taskId, userId);

        model.setUserId(userId);
        model.setStatusId(taskDTO.getStatusId());
        model.setTitle(taskDTO.getTitle());
        model.setDescription(taskDTO.getDescription());

        taskService.update(model);
        return responseFactory.makeSuccessResponse();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> removeTask(
            Authentication authentication,
            @PathVariable @NonNull Long taskId
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskModel task = taskService.find(taskId, userId);
        taskService.remove(task.getId());
        return responseFactory.makeSuccessResponse();
    }

    // Comments

    @PostMapping("/{taskId}/comments")
    public ResponseEntity<BaseResponse<TaskCommentDTO>> createComment(
            Authentication authentication,
            @PathVariable @NonNull Long taskId,
            @RequestBody @Valid CreateTaskCommentDTO commentDTO
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskCommentModel model = taskCommentService.create(commentDTO, userId, taskId);
        return responseFactory.makeSuccessResponse(new TaskCommentDTO(model));
    }

    @GetMapping("/{taskId}/comments/{commentId}")
    public ResponseEntity<BaseResponse<TaskCommentDTO>> getComment(
            Authentication authentication,
            @PathVariable @NonNull Long taskId,
            @PathVariable @NonNull Long commentId
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskCommentModel model = taskCommentService.get(commentId, taskId, userId);
        return responseFactory.makeSuccessResponse(new TaskCommentDTO(model));
    }

    @DeleteMapping("/{taskId}/comments/{commentId}")
    public ResponseEntity<?> removeComment(
            Authentication authentication,
            @PathVariable @NonNull Long taskId,
            @PathVariable @NonNull Long commentId
    ) {
        Long userId = userService.getId(authentication.getName());
        TaskCommentModel commentModel = taskCommentService.get(commentId, taskId, userId);
        taskCommentService.remove(commentModel.getId());
        return responseFactory.makeSuccessResponse();
    }
}
