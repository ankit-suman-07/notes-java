package com.notes.demo.controllers;

import com.notes.demo.dto.CreateNoteDTO;
import com.notes.demo.dto.CreateNoteResponseDTO;
import com.notes.demo.entities.NoteEntity;
import com.notes.demo.service.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks/{taskId}/notes")
public class NotesController {

    private NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("/get-notes")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId) {
        var notes = notesService.getNotesForTask(taskId);

        return ResponseEntity.ok(notes);
    }

    @PostMapping("/create-note")
    public ResponseEntity<CreateNoteResponseDTO> createNotes(@PathVariable("taskId") Integer taskId,
                                                             @RequestBody CreateNoteDTO createNoteDTO) {
        var note = notesService.addNoteForTask(taskId, createNoteDTO.getTitle(), createNoteDTO.getBody());

        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, note));
    }

}
