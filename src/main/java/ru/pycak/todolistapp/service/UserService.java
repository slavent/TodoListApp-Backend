package ru.pycak.todolistapp.service;

import ru.pycak.todolistapp.dto.CreateUserDTO;
import ru.pycak.todolistapp.model.UserModel;

public interface UserService {

    /**
     * Get User model by identifier.
     *
     * @param id User unique identifier
     * @return User instance
     */
    public UserModel get(Long id);

    /**
     * Get User model by email.
     *
     * @param email User email
     * @return User instance
     */
    public UserModel get(String email);

    /**
     * Get User id by email.
     *
     * @param email User email
     * @return User identifier
     */
    public Long getId(String email);

    /**
     * Add new User instance to database.
     *
     * @param createUserDTO New user DTO
     * @return Created User instance
     */
    public UserModel create(CreateUserDTO createUserDTO);

    /**
     * Update user if one already exists.
     *
     * @param userModel User model
     */
    public void update(UserModel userModel);

    /**
     * Remove user by id.
     *
     * @param id User unique identifier
     */
    public void remove(Long id);
}
