package ru.pycak.todolistapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.entity.User;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(user);
    }

    @Override
    public User get(int id) {
        return sessionFactory.getCurrentSession()
                .get(User.class, id);
    }

    @Override
    public void delete(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete from User where id=:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }
}
