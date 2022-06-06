package ru.pycak.todolistapp.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.entity.TaskStatus;

import javax.persistence.EntityManager;

@Repository
public class TaskStatusDAOImpl implements TaskStatusDAO {

    private final EntityManager entityManager;

    @Autowired
    public TaskStatusDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public TaskStatus get(int id) {
        return entityManager.unwrap(Session.class)
                .get(TaskStatus.class, id);
    }
}
