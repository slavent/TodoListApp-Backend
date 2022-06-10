package ru.pycak.todolistapp.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {

    private final EntityManager entityManager;

    @Autowired
    public TaskDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Task task) {
        entityManager.unwrap(Session.class)
                .saveOrUpdate(task);
    }

    @Override
    public List<Task> getTasksByUser(Long id) {
        return entityManager.unwrap(Session.class)
                .createQuery("from Task where user.id=:userId", Task.class)
                .setParameter("userId", id)
                .getResultList();
    }

    @Override
    public Task get(Long id) {
        return entityManager.unwrap(Session.class)
                .get(Task.class, id);
    }

    @Override
    public void remove(Long id) {
        entityManager.unwrap(Session.class)
                .createQuery("delete from Task where id=:taskId")
                .setParameter("taskId", id)
                .executeUpdate();
    }
}
