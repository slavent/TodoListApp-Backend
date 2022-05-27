package ru.pycak.todolistapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.entity.TaskComment;

@Repository
public class TaskCommentDAOImpl implements TaskCommentDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public TaskCommentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(TaskComment comment) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(comment);
    }

    @Override
    public TaskComment get(Long id) {
        return sessionFactory.getCurrentSession()
                .get(TaskComment.class, id);
    }

    @Override
    public void remove(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from TaskComment where id=:taskCommentId")
                .setParameter("taskCommentId", id)
                .executeUpdate();
    }
}
