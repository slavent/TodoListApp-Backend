package ru.pycak.todolistapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.entity.TaskComment;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class TaskCommentDAOImpl implements TaskCommentDAO {

    private final EntityManager entityManager;

    @Autowired
    public TaskCommentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(TaskComment comment) {
        entityManager.merge(comment);
    }

    @Override
    public TaskComment get(Long id) {
        return entityManager.find(TaskComment.class, id);
    }

    @Override
    public Optional<TaskComment> findByIdAndTaskIdAndUserId(Long commentId, Long taskId, Long userId) {
        return entityManager
                .createQuery("from TaskComment where id=:commentId " +
                        "and userId=:userId and taskId=:taskId", TaskComment.class)
                .setParameter("commentId", commentId)
                .setParameter("userId", userId)
                .setParameter("taskId", taskId)
                .getResultStream()
                .findAny();
    }

    @Override
    public void remove(Long id) {
        entityManager
                .createQuery("delete from TaskComment where id=:taskCommentId")
                .setParameter("taskCommentId", id)
                .executeUpdate();
    }
}
