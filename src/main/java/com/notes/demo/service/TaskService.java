package com.notes.demo.service;

import com.notes.demo.entities.TaskEntity;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.*;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    @Getter
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;
    private SimpleDateFormat deadlineFormat = new SimpleDateFormat("YYYY-MM-DD");

    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskId);
        taskEntity.setTitle(title);
        taskEntity.setDescription(description);
        taskEntity.setDeadline(deadlineFormat.parse(deadline)); // TODO: validate date format yyyy-mm-dd
        taskEntity.setCompleted(false);
        tasks.add(taskEntity);
        taskId++;
        return taskEntity;
    }

    public TaskEntity getTaskById(int id) {
        tasks.stream().findAny().filter(taskEntity -> taskEntity.getId() == id).orElse(null);
        for(TaskEntity taskEntity: tasks) {
            if(taskEntity.getId() == id) {
                return taskEntity;
            }
        }
        return null;
    }

    public TaskEntity updateTaskBy(int id, String description, String deadline, boolean completed) throws ParseException {
        TaskEntity taskEntity = getTaskById(id);

        if(taskEntity == null) {
            return null;
        }
        taskEntity.setDescription(description == null? taskEntity.getDescription() : description);
        taskEntity.setDeadline(deadline == null? taskEntity.getDeadline() : deadlineFormat.parse(deadline));
        taskEntity.setCompleted(completed);

        return taskEntity;
    }
}
