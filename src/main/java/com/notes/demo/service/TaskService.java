package com.notes.demo.service;

import com.notes.demo.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;

    void addTask(String title, String description, String deadline) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskId);
        taskEntity.setTitle(title);
        taskEntity.setDescription(description);
        taskEntity.setDeadline(new Date(deadline)); // TODO: validate date format yyyy-mm-dd
        taskEntity.setCompleted(false);
        tasks.add(taskEntity);
        taskId++;
    }

    ArrayList<TaskEntity> getTasks() {
        return tasks;
    }

    TaskEntity getTaskById(int id) {
        tasks.stream().findAny().filter(taskEntity -> taskEntity.getId() == id).orElse(null);
        for(TaskEntity taskEntity: tasks) {
            if(taskEntity.getId() == id) {
                return taskEntity;
            }
        }
        return null;
    }
}
