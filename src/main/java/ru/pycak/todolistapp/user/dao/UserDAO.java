package ru.pycak.todolistapp.user.dao;

import ru.pycak.todolistapp.user.entity.User;

import java.util.Optional;

public interface UserDAO {

    /**
     * Save User or update if one already exists in database
     *
     * @param user User instance
     * @return saved User instance
     */
    public User save(User user);

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
     * @return First User with given email, or Optional.empty().
     */
    public Optional<User> findByEmail(String email);
}
