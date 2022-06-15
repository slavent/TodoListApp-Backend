package ru.pycak.todolistapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class CreateTaskDTO {

    @NotBlank
    private final String title;

    private final String description;

    private final int statusId;
}
