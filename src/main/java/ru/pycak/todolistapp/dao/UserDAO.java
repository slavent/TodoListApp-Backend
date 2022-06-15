package ru.pycak.todolistapp.dao;

import ru.pycak.todolistapp.entity.User;

public interface UserDAO {

    /**
     * Save User or update if one already exists in database
     *
     * @param user User instance
     */
    public void save(User user);

    /**
     * @param id User unique identifier
     * @return User instance
     */
    public User get(Long id);

    /**
     * Remove User from database by its identifier
     *
     * @param id User unique identifier
     */
    public void remove(Long id);

    /**
     * @param email Email string
     * @return First User with given email, or null.
     */
    public User findByEmail(String email);
}
