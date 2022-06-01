package ru.pycak.todolistapp.dao;

import ru.pycak.todolistapp.entity.TaskComment;

import java.util.List;

public interface TaskCommentDAO {

    public List<TaskComment> getAll();

    public void save(TaskComment task);

    public TaskComment get(int id);

    public void remove(int id);
}
