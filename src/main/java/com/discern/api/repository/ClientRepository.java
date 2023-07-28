package com.discern.api.repository;

import com.discern.api.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findAllByCompanyId(Long companyId, Pageable pageable);

    Optional<Client> findByIdAndCompanyId(Long id,Long companyId);

}