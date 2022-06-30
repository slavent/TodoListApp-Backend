package ru.pycak.todolistapp.user.dao;

import ru.pycak.todolistapp.user.entity.Role;

public interface RoleDAO {

    Role get(int roleId);

    Role getRoleUser();
}
