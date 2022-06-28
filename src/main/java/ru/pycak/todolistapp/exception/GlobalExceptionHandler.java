package ru.pycak.todolistapp.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.pycak.todolistapp.auth.exception.JwtAuthenticationException;
import ru.pycak.todolistapp.response.ErrorResponseFactory;
import ru.pycak.todolistapp.tasks.exception.TaskCommentNotFoundException;
import ru.pycak.todolistapp.tasks.exception.TaskDoesNotExistException;
import ru.pycak.todolistapp.tasks.exception.UnknownStatusException;
import ru.pycak.todolistapp.user.exception.UserAlreadyExistsException;
import ru.pycak.todolistapp.user.exception.UserDoesNotExistException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorResponseFactory builder;

    @ExceptionHandler
    public ResponseEntity<?> handleAccessDenied(AccessDeniedException e) {
        log.error(e.getMessage());
        return builder.makeUnauthorizedResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleJwt(JwtAuthenticationException e) {
        log.error(e.getMessage());
        return builder.makeUnauthorizedResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleAuthentication(AuthenticationException e) {
        log.error(e.getMessage());
        return builder.makeUnauthorizedResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleTaskCommentNotFound(TaskCommentNotFoundException e) {
        log.error(e.getMessage());
        return builder.makeNotFoundResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleTaskNotFound(TaskDoesNotExistException e) {
        log.error(e.getMessage());
        return builder.makeNotFoundResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleUnknownStatus(UnknownStatusException e) {
        log.error(e.getMessage());
        return builder.makeBadRequestResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleUserNotFound(UserDoesNotExistException e) {
        log.error(e.getMessage());
        return builder.makeNotFoundResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleUserExists(UserAlreadyExistsException e) {
        log.error(e.getMessage());
        return builder.makeBadRequestResponse(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidArgument(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        List<ValidationErrorInfo> errors = e.getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationErrorInfo(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .collect(Collectors.toList());

        return builder.makeBadRequestResponse(errors, "Invalid parameter(s) for the request");
    }

    @ExceptionHandler
    public ResponseEntity<?> handleOther(Exception e) {
        log.error(e.getMessage());
        return builder.makeInternalErrorResponse();
    }
}
