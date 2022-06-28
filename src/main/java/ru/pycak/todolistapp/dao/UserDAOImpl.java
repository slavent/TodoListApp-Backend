package ru.pycak.todolistapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.entity.User;

import javax.persistence.EntityManager;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User user) {
        entityManager.merge(user);
    }

    @Override
    public User get(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void remove(Long id) {
        entityManager
                .createQuery("delete from User where id=:userId")
                .setParameter("userId", id)
                .executeUpdate();
    }

    @Override
    public User findByEmail(String email) {
        return entityManager
                .createQuery("from User where email=:userEmail", User.class)
                .setParameter("userEmail", email)
                .getResultStream()
                .findAny()
                .orElse(null);
    }
}
