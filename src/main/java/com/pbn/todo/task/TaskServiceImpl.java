package com.pbn.todo.task;

import com.pbn.todo.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pbn.todo.task.Status.ACTIVE;
import static com.pbn.todo.task.Status.COMPLETE;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    @Override
    public Task newTask(Task task) {
        task.setStatus(Status.ACTIVE);
        return repository.save(task);
    }

    @Override
    public Task one(Long id) {
        return getOrElseThrow(id);
    }

    @Override
    public Task update(Long id, Task newTask) {
        Task task = getOrElseThrow(id);
        task.setName(newTask.getName());
        return repository.save(task);
    }

    @Override
    public void delete(Long id) {
        Task task = getOrElseThrow(id);
        repository.delete(task);
    }

    @Override
    public List<Task> all() {
        return repository.findAll();
    }

    @Override
    public Task complete(Long id) {
        Task task = getOrElseThrow(id);
        task.setStatus(COMPLETE);
        return repository.save(task);
    }

    @Override
    public Task activate(Long id) {
        Task task = getOrElseThrow(id);
        task.setStatus(ACTIVE);
        return repository.save(task);
    }

    private Task getOrElseThrow(Long id)     {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Task.class, "id", id));
    }
}
