package com.pbn.todo.task;

import java.util.List;

public interface TaskService {
    Task newTask(Task task);
    Task one(Long id);
    Task update(Long id, Task newTask);
    void delete(Long id);
    List<Task> all();
    Task complete(Long id);
    Task activate(Long id);
}
