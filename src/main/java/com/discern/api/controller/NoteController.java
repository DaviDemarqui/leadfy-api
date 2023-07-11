package com.discern.api.controller;

import com.discern.api.dto.NoteDTO;
import com.discern.api.service.NoteService;
import com.discern.api.service.NoteService;
import com.discern.api.service.TokenVerifyingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    private final TokenVerifyingService tokenVerifyingService;

    @GetMapping
    public ResponseEntity<?> getAllNotes(NoteDTO noteDTO, Pageable pageable) {
//        tokenVerifyingService.validateCompanyId(token, noteDTO.getCompanyId());
        return ResponseEntity.ok(noteService.getAllNotes(noteDTO, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAllNotes(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> postNote(@RequestBody NoteDTO noteDTO, @RequestHeader("Authorization") String token) {
        tokenVerifyingService.validateCompanyId(token, noteDTO.getCompanyId());
        return ResponseEntity.ok(noteService.saveOrUpdate(noteDTO, null));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putNote(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        return ResponseEntity.ok(noteService.saveOrUpdate(noteDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
