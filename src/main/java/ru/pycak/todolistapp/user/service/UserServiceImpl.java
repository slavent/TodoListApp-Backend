package ru.pycak.todolistapp.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pycak.todolistapp.user.dao.RoleDAO;
import ru.pycak.todolistapp.user.dao.UserDAO;
import ru.pycak.todolistapp.user.dto.CreateUserDTO;
import ru.pycak.todolistapp.user.entity.Role;
import ru.pycak.todolistapp.user.entity.User;
import ru.pycak.todolistapp.user.exception.UserAlreadyExistsException;
import ru.pycak.todolistapp.user.exception.UserDoesNotExistException;
import ru.pycak.todolistapp.user.model.UserModel;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserModel get(Long id) {
        return new UserModel(userDAO.get(id));
    }

    @Override
    @Transactional
    public UserModel get(String email) {
        return userDAO
                .findByEmail(email)
                .map(UserModel::new)
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
    public UserModel create(CreateUserDTO createUserDTO) {
        if (userDAO.findByEmail(createUserDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "User with email '"+createUserDTO.getEmail()+"' already exists"
            );
        }

        Role roleUser = roleDAO.getRoleUser();
        User user = new User();
        user.setRoles(Set.of(roleUser));
        user.setName(createUserDTO.getName());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(
                createUserDTO.getPassword()
        ));

        return new UserModel(userDAO.save(user));
    }

    @Override
    @Transactional
    public void update(UserModel userModel) {
        User user = userDAO.get(userModel.getId());
        if (user == null) {
            throw new UserDoesNotExistException("User with id '"+userModel.getId()+"' does not exists");
        }

        if (userModel.getName() != null) {
            user.setName(userModel.getName());
        }
        if (userModel.getEmail() != null) {
            user.setEmail(userModel.getEmail());
        }
        if (userModel.getAvatarUrl() != null) {
            user.setAvatarUrl(userModel.getAvatarUrl());
        }

        userDAO.save(user);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        userDAO.remove(id);
    }
}
