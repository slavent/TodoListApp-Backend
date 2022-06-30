package ru.pycak.todolistapp.tasks.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.tasks.entity.TaskStatus;

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
        return entityManager.find(TaskStatus.class, id);
    }
}
