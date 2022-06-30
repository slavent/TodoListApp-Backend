package ru.pycak.todolistapp.dto.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public final class CreateTaskDTO {

    @NotBlank
    private final String title;

    @NotBlank
    private final String description;

    @NotBlank
    private final int statusId;
}
