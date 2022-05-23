package ru.pycak.todolistapp.model;

import lombok.Data;

import java.util.Date;

@Data
public class Task {

    private final Long id;
    private final String title;
    private final String description;
    private final String userId;
    private final String statusId;
    private final Date creationDate;
    private Date editDate;
}
