package com.notes.demo.controllers;

import com.notes.demo.entities.TaskEntity;
import com.notes.demo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/get-tasks")
    public ResponseEntity<List<TaskEntity>> getTasks() {
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/get-all-tasks")
    public List<TaskEntity> getTasksList() {
        var tasks = taskService.getTasks();
        return tasks;
    }

    @GetMapping("/{id")
    public ResponseEntity<TaskEntity> getTaskById(int id) {
        var task = taskService.getTaskById(id);
        if(task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/{id")
    public ResponseEntity<TaskEntity> addTask(){
        taskService.addTask(String title, String description, String deadline);
    }
}
