package ru.pycak.todolistapp.model;

import lombok.Data;

@Data
public class User {

    private final Long id;
    private final  String name;
    private final  String login;
    private final String password;
    private final String email;
    private final String avatarUrl;
}
