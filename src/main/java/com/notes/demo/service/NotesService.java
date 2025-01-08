package com.notes.demo.service;

import com.notes.demo.entities.NoteEntity;
import com.notes.demo.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NotesService {
    private TaskService taskService;
    private HashMap<Integer, TaskNotesHolder> taskNoteHolder = new HashMap<>();

    public NotesService(TaskService taskService) {
        this.taskService = taskService;
    }

    class TaskNotesHolder {
        protected int noteId = 1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();
    }

    public List<NoteEntity> getNotesForTask(int taskId) {
        TaskEntity taskEntity = taskService.getTaskById(taskId);

        if(taskEntity == null) {
            return null;
        }

        if(taskNoteHolder.get(taskId) == null) {
            taskNoteHolder.put(taskId, new TaskNotesHolder());
        }

        return taskNoteHolder.get(taskId).notes;
    }

    public NoteEntity addNoteForTask(int taskId, String title, String body) {
        TaskEntity taskEntity = taskService.getTaskById(taskId);

        if(taskEntity == null) {
            return null;
        }

        if(taskNoteHolder.get(taskId) == null) {
            taskNoteHolder.put(taskId, new TaskNotesHolder());
        }

        TaskNotesHolder taskNotesHolder = taskNoteHolder.get(taskId);
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(taskNotesHolder.noteId);
        noteEntity.setTitle(title);
        noteEntity.setBody(body);

        taskNotesHolder.notes.add(noteEntity);
        taskNotesHolder.noteId++;

        return noteEntity;
    }
}
