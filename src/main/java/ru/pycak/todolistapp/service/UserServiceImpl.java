package ru.pycak.todolistapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pycak.todolistapp.dao.UserDAO;
import ru.pycak.todolistapp.dto.CreateUserDTO;
import ru.pycak.todolistapp.dto.UserDTO;
import ru.pycak.todolistapp.entity.User;
import ru.pycak.todolistapp.exception.UserAlreadyExistsException;
import ru.pycak.todolistapp.exception.UserDoesNotExistException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO get(Long id) {
        return convertToDto(userDAO.get(id));
    }

    @Override
    @Transactional
    public UserDTO get(String email) {
        return userDAO
                .findByEmail(email)
                .map(this::convertToDto)
                .orElseThrow(() -> new UserDoesNotExistException(
                        "User with email '"+email+"' not found"
                ));
    }

    @Override
    @Transactional
    public Long getId(String email) {
        return userDAO
                .findByEmail(email)
                .map(User::getId)
                .orElseThrow(() -> new UserDoesNotExistException(
                        "User with email '"+email+"' not found"
                ));
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO) {
        User user = userDAO.get(userDTO.getId());
        if (user == null) {
            throw new UserDoesNotExistException("User with id '"+userDTO.getId()+"' does not exists");
        }

        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getAvatarUrl() != null) {
            user.setAvatarUrl(userDTO.getAvatarUrl());
        }

        userDAO.save(user);
    }

    @Override
    @Transactional
    public UserDTO create(CreateUserDTO createUserDTO) {
        if (userDAO.findByEmail(createUserDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email '"+createUserDTO.getEmail()+"' already exists");
        }

        User user = new User();
        user.setName(createUserDTO.getName());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(
                createUserDTO.getPassword()
        ));

        userDAO.save(user);
        return convertToDto(user);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        userDAO.remove(id);
    }

    private UserDTO convertToDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAvatarUrl()
        );
    }
}
