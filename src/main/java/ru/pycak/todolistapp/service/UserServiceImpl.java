package ru.pycak.todolistapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pycak.todolistapp.dao.UserDAO;
import ru.pycak.todolistapp.entity.User;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public User getUser(Long id) {
        return userDAO.get(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void create(User user) {
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        userDAO.remove(id);
    }
}
