package ru.pycak.todolistapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(user);
    }

    @Override
    public User get(Long id) {
        return sessionFactory.getCurrentSession()
                .get(User.class, id);
    }

    @Override
    public void remove(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from User where id=:userId")
                .setParameter("userId", id)
                .executeUpdate();
    }
}
