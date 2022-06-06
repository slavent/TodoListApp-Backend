package ru.pycak.todolistapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.entity.TaskStatus;

@Repository
public class TaskStatusDAOImpl implements TaskStatusDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public TaskStatusDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TaskStatus get(int id) {
        return sessionFactory.getCurrentSession()
                .get(TaskStatus.class, id);
    }
}
