package ru.pycak.todolistapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.entity.Task;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public TaskDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Task> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Task", Task.class)
                .getResultList();
    }

    @Override
    public List<Task> getUserTasks(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Task where user.id=id")
                .getResultList();
    }

    @Override
    public void save(Task task) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(task);
    }

    @Override
    public Task get(int id) {
        return sessionFactory.getCurrentSession()
                .get(Task.class, id);
    }

    @Override
    public void remove(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete from Task where id=:taskId");
        query.setParameter("taskId", id);
        query.executeUpdate();
    }
}
