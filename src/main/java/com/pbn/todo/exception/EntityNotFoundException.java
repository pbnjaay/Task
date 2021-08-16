package com.pbn.todo.exception;

import com.pbn.todo.task.Task;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<Task> clazz, String paramName, Long paramValue) {
        super(clazz.getSimpleName() + " with the given " + paramName + " {" + paramValue + "}" + " was not found");
    }
}
