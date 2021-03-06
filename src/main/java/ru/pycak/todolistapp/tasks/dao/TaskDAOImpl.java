package ru.pycak.todolistapp.tasks.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.tasks.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskDAOImpl implements TaskDAO {

    private final EntityManager entityManager;

    @Autowired
    public TaskDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Task save(Task task) {
        return entityManager.merge(task);
    }

    @Override
    public List<Task> getTasksByUser(Long id) {
        return entityManager
                .createQuery("from Task where user.id=:userId", Task.class)
                .setParameter("userId", id)
                .getResultList();
    }

    @Override
    public Task get(Long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public Optional<Task> findByIdAndUserId(Long taskId, Long userId) {
        return entityManager
                .createQuery("from Task where id=:taskId and user.id=:userId", Task.class)
                .setParameter("taskId", taskId)
                .setParameter("userId", userId)
                .getResultStream()
                .findAny();
    }

    @Override
    public void remove(Long id) {
        entityManager
                .createQuery("delete from Task where id=:taskId")
                .setParameter("taskId", id)
                .executeUpdate();
    }
}
