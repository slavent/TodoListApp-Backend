package ru.pycak.todolistapp.service;

import ru.pycak.todolistapp.dto.CreateUserDTO;
import ru.pycak.todolistapp.dto.UserDTO;

public interface UserService {

    /**
     * Get User model by identifier.
     *
     * @param id User unique identifier
     * @return User instance
     */
    public UserDTO get(Long id);

    /**
     * Get User model by email.
     *
     * @param email User email
     * @return User instance
     */
    public UserDTO get(String email);

    /**
     * Get User id by email.
     *
     * @param email User email
     * @return User identifier
     */
    public Long getId(String email);

    /**
     * Update user if one already exists.
     *
     * @param user User model
     */
    public void update(UserDTO user);

    /**
     * Add new User instance to database.
     *
     * @param createUserDTO New user DTO
     * @return Created User instance
     */
    public UserDTO create(CreateUserDTO createUserDTO);

    /**
     * Remove user by id.
     *
     * @param id User unique identifier
     */
    public void remove(Long id);
}
