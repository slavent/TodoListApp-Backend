package ru.pycak.todolistapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.entity.Task;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public TaskDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Task task) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(task);
    }

    @Override
    public List<Task> getTasksByUser(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Task where user.id=:userId", Task.class)
                .setParameter("userId", id)
                .getResultList();
    }

    @Override
    public Task get(Long id) {
        return sessionFactory.getCurrentSession()
                .get(Task.class, id);
    }

    @Override
    public void remove(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from Task where id=:taskId")
                .setParameter("taskId", id)
                .executeUpdate();
    }
}
