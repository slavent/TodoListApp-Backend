package ru.pycak.todolistapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ShortTaskDTO {

    private final Long id;
    private final Long userId;
    private final int statusId;
    private final String title;
}
