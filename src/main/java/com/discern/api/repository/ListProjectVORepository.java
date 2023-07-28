package com.discern.api.repository;

import com.discern.api.view.ListProjectVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListProjectVORepository extends JpaRepository<ListProjectVO, Long> {
    Page<ListProjectVO> findAllByCompanyId(Long companyId, Pageable pageable);
}