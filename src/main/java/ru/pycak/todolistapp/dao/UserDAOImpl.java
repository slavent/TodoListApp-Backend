package ru.pycak.todolistapp.dao;

import org.hibernate.Session;
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
        entityManager.unwrap(Session.class)
                .saveOrUpdate(user);
    }

    @Override
    public User get(Long id) {
        return entityManager.unwrap(Session.class)
                .get(User.class, id);
    }

    @Override
    public void remove(Long id) {
        entityManager.unwrap(Session.class)
                .createQuery("delete from User where id=:userId")
                .setParameter("userId", id)
                .executeUpdate();
    }
}
