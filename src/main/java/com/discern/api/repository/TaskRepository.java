package com.discern.api.repository;

import com.discern.api.model.Note;
import com.discern.api.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByCompanyId(Long companyId, Pageable pageable);

    Optional<Task> findByIdAndCompanyId(Long id, Long companyId);

    void deleteByIdAndCompanyId(Long id, Long companyId);
}