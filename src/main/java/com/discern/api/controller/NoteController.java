package com.discern.api.controller;

import com.discern.api.dto.NoteDTO;
import com.discern.api.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Davi
 * @created 11/jul./2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_NOTE_READ')")
    public ResponseEntity<?> getAllNotes(Pageable pageable) {
        return ResponseEntity.ok(noteService.getAllNotes(pageable));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_NOTE_READ')")
    public ResponseEntity<?> getAllNotes(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_NOTE_CREATE')")
    public ResponseEntity<?> postNote(@RequestBody NoteDTO noteDTO) {
        return ResponseEntity.ok(noteService.saveOrUpdate(noteDTO, null));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_NOTE_UPDATE')")
    public ResponseEntity<?> putNote(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        return ResponseEntity.ok(noteService.saveOrUpdate(noteDTO, id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_NOTE_DELETE')")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
