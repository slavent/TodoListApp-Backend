package ru.pycak.todolistapp.service;

import ru.pycak.todolistapp.dto.CreateUserDTO;
import ru.pycak.todolistapp.dto.UserDTO;

public interface UserService {

    /**
     * Get User info by identifier
     *
     * @param id User unique identifier
     * @return User instance
     */
    public UserDTO getUser(Long id);

    /**
     * Update user if one already exists
     *
     * @param user UserDTO instance
     */
    public void update(UserDTO user);

    /**
     * Add new User instance to database
     *
     * @param createUserDTO New user DTO
     * @return Created User instance
     */
    public UserDTO createUser(CreateUserDTO createUserDTO);

    /**
     * Remove user by id
     *
     * @param id User unique identifier
     */
    public void removeUser(Long id);
}
