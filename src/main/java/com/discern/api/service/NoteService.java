package com.discern.api.service;

import com.discern.api.dto.NoteDTO;
import com.discern.api.exceptions.NoteNotFoundException;
import com.discern.api.model.Note;
import com.discern.api.repository.NoteRepository;
import com.discern.api.repository.NoteRepository;
import com.discern.api.utils.mapper.MapperUtil;
import com.discern.api.utils.matcher.TiposExampleMatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoteService {

    private final MapperUtil mapperUtil;
    private final NoteRepository noteRepository;

    public Page<NoteDTO> getAllNotes(NoteDTO noteDTO, Pageable pageable) {
        var example = Example.of(mapperUtil.mapTo(noteDTO, Note.class),
                TiposExampleMatcher.exampleMatcherMatchingAny());

        return noteRepository.findAll(example, pageable)
                .map(Note -> mapperUtil.mapTo(Note, NoteDTO.class));
    }

    public NoteDTO findById(Long id) {
        return mapperUtil.mapTo(noteRepository.findById(id)
                .orElseThrow(NoteNotFoundException::new), NoteDTO.class);
    }

    public NoteDTO saveOrUpdate(NoteDTO noteDTO, Long id) {
        Note noteSave;

        if(id != null) {
            noteSave = noteRepository.findById(id).orElseThrow(NoteNotFoundException::new);
            Note pDTO = mapperUtil.mapTo(noteDTO, Note.class);
            BeanUtils.copyProperties(pDTO, noteSave, "id");
        } else {
            noteSave = mapperUtil.mapTo(noteDTO, Note.class);
        }

        return mapperUtil.mapTo(noteRepository.save(noteSave), NoteDTO.class);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
