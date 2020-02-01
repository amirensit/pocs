package fr.ntech.jwtspringsec.web;

import fr.ntech.jwtspringsec.entities.Task;
import fr.ntech.jwtspringsec.repositories.TaskRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskRestController {

    private TaskRepository taskRepository;

    public TaskRestController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public List<Task> listTasks() {
        return this.taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task save(@RequestBody Task task) {
        return this.taskRepository.save(task);
    }


}
