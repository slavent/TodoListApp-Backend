package ru.pycak.todolistapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pycak.todolistapp.dao.TaskCommentDAO;
import ru.pycak.todolistapp.entity.TaskComment;

import javax.transaction.Transactional;

@Service
public class TaskCommentServiceImpl implements TaskCommentService {

    private final TaskCommentDAO taskCommentDAO;

    @Autowired
    public TaskCommentServiceImpl(TaskCommentDAO taskCommentDAO) {
        this.taskCommentDAO = taskCommentDAO;
    }

    @Override
    @Transactional
    public void save(TaskComment comment) {
        taskCommentDAO.save(comment);
    }

    @Override
    @Transactional
    public void removeComment(Long id) {
        taskCommentDAO.remove(id);
    }
}
