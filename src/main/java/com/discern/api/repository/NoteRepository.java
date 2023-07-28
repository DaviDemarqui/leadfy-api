package com.discern.api.repository;

import com.discern.api.model.Client;
import com.discern.api.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Page<Note> findAllByCompanyId(Long companyId, Pageable pageable);

    Optional<Note> findByIdAndCompanyId(Long id, Long companyId);

    void deleteByIdAndCompanyId(Long id, Long companyId);
}