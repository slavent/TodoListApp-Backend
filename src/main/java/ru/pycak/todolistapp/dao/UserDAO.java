package ru.pycak.todolistapp.dao;

import ru.pycak.todolistapp.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAll();

    public void save(User user);

    public User get(int id);

    public void delete(int id);
}
