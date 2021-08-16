package com.pbn.todo.task;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService service;
    private final TaskModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Task>> all() {
        return assembler.toCollectionModel(service.all());
    }

    @GetMapping("/{id}")
    public EntityModel<Task> one(@PathVariable Long id) {
        return assembler.toModel(service.one(id));
    }

    @PostMapping
    public ResponseEntity<?> newTask(@RequestBody Task task){
        EntityModel<Task> taskEntityModel = assembler.toModel(service.newTask(task));
        return getResponse(taskEntityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Task newTask){
        EntityModel<Task> taskEntityModel = assembler.toModel(service.update(id, newTask));
        return getResponse(taskEntityModel);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> complete(@PathVariable Long id) {
        EntityModel<Task> taskEntityModel = assembler.toModel(service.complete(id));
        return getResponse(taskEntityModel);
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activate(@PathVariable Long id) {
        EntityModel<Task> taskEntityModel = assembler.toModel(service.activate(id));
        return getResponse(taskEntityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<EntityModel<Task>> getResponse(EntityModel<Task> taskEntityModel) {
        return ResponseEntity
                .created(taskEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(taskEntityModel);
    }
}
