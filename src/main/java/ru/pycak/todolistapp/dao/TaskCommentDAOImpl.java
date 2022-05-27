package ru.pycak.todolistapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.pycak.todolistapp.entity.Task;
import ru.pycak.todolistapp.entity.TaskComment;

import javax.persistence.Query;
import java.util.List;

public class TaskCommentDAOImpl implements TaskCommentDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public TaskCommentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<TaskComment> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from TaskComment", TaskComment.class)
                .getResultList();
    }

    @Override
    public void save(TaskComment comment) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(comment);
    }

    @Override
    public TaskComment get(int id) {
        return sessionFactory.getCurrentSession()
                .get(TaskComment.class, id);
    }

    @Override
    public void remove(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete from TaskComment where id=:taskCommentId");
        query.setParameter("taskCommentId", id);
        query.executeUpdate();
    }
}
