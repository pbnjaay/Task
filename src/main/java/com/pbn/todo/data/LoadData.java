package com.pbn.todo.data;

import com.pbn.todo.task.Task;
import com.pbn.todo.task.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.pbn.todo.task.Status.ACTIVE;
import static com.pbn.todo.task.Status.COMPLETE;

@Configuration
@Slf4j
public class LoadData {
    @Bean
    CommandLineRunner initDatabase(TaskRepository repository){
        return args -> {
            log.info("Preloading" + repository.save(new Task("sleep", ACTIVE)));
            log.info("Preloading" + repository.save(new Task("eat", COMPLETE)));
            log.info("Preloading" + repository.save(new Task("code", ACTIVE)));
        };
    }
}
