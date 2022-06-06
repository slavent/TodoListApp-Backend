package ru.pycak.todolistapp.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.entity.TaskComment;

import javax.persistence.EntityManager;

@Repository
public class TaskCommentDAOImpl implements TaskCommentDAO {

    private final EntityManager entityManager;

    @Autowired
    public TaskCommentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(TaskComment comment) {
        entityManager.unwrap(Session.class)
                .saveOrUpdate(comment);
    }

    @Override
    public TaskComment get(Long id) {
        return entityManager.unwrap(Session.class)
                .get(TaskComment.class, id);
    }

    @Override
    public void remove(Long id) {
        entityManager.unwrap(Session.class)
                .createQuery("delete from TaskComment where id=:taskCommentId")
                .setParameter("taskCommentId", id)
                .executeUpdate();
    }
}
