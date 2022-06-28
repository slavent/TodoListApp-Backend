package ru.pycak.todolistapp.tasks.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public final class CreateTaskDTO {

    @NotNull
    @NotBlank
    private final String title;

    @NotNull
    @NotBlank
    private final String description;

    @NotNull
    private final Integer statusId;
}
