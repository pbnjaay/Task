package com.pbn.todo.task;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static com.pbn.todo.task.Status.ACTIVE;
import static com.pbn.todo.task.Status.COMPLETE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskModelAssembler implements RepresentationModelAssembler<Task, EntityModel<Task>> {
    @Override
    public EntityModel<Task> toModel(Task task) {
        EntityModel<Task> taskModel = EntityModel.of(
                task,
                linkTo(methodOn(TaskController.class).one(task.getId())).withSelfRel(),
                linkTo(methodOn(TaskController.class).all()).withRel("tasks")
        );

        if (task.getStatus() == ACTIVE)
            taskModel.add(linkTo(methodOn(TaskController.class).complete(task.getId())).withRel("complete"));

        if(task.getStatus() == COMPLETE)
                taskModel.add(linkTo(methodOn(TaskController.class).activate(task.getId())).withRel("activate"));
        return taskModel;
    }

    @Override
    public CollectionModel<EntityModel<Task>> toCollectionModel(Iterable<? extends Task> tasks) {
        CollectionModel<EntityModel<Task>> entityModels = RepresentationModelAssembler.super.toCollectionModel(tasks);
        return entityModels.add(linkTo(methodOn(TaskController.class).all()).withSelfRel());
    }
}
