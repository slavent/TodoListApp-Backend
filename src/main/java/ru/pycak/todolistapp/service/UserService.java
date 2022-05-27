package ru.pycak.todolistapp.service;

import ru.pycak.todolistapp.entity.User;

public interface UserService {

    /**
     * Get User info by identifier
     *
     * @param id User unique identifier
     * @return User instance
     */
    public User getUser(Long id);

    /**
     * Update user if one already exists
     *
     * @param user User instance
     */
    public void update(User user);

    /**
     * Add new User instance to database
     *
     * @param user User instance without id parameter
     */
    public void create(User user);

    /**
     * Remove user by id
     *
     * @param id User unique identifier
     */
    public void removeUser(Long id);
}
