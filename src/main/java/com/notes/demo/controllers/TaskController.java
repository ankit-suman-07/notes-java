package com.notes.demo.controllers;

import com.notes.demo.dto.CreateTaskDTO;
import com.notes.demo.dto.ErrorResponseDTO;
import com.notes.demo.dto.UpdateTaskDTO;
import com.notes.demo.entities.TaskEntity;
import com.notes.demo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id) {
        var task = taskService.getTaskById(id);
        if(task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO createTaskDTO) throws ParseException {
        var task = taskService.addTask(createTaskDTO.getTitle(), createTaskDTO.getDescription(), createTaskDTO.getDeadline());
        return ResponseEntity.ok(task);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id,
                                                 @RequestBody UpdateTaskDTO updateTaskDTO) throws ParseException {
        var task = taskService.updateTaskBy(id, updateTaskDTO.getDescription(), updateTaskDTO.getDeadline(), updateTaskDTO.getCompleted());

        if(task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e) {
        if(e instanceof ParseException) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date Formatter!!!"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server"));
    }
}
