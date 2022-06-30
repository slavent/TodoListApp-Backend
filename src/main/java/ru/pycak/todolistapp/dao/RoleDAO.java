package ru.pycak.todolistapp.dao;

import ru.pycak.todolistapp.entity.Role;

public interface RoleDAO {

    Role get(int roleId);

    Role getRoleUser();
}
