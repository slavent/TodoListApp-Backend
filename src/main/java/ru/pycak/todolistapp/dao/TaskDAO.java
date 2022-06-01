package ru.pycak.todolistapp.dao;

import ru.pycak.todolistapp.entity.Task;

import java.util.List;

public interface TaskDAO {

    public void save(Task task);

    public List<Task> getAll();

    public List<Task> getUserTasks(int id);

    public Task get(int id);

    public void remove(int id);
}
