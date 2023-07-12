package com.discern.api.repository;

import com.discern.api.view.ListProjectVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListProjectVORepository extends JpaRepository<ListProjectVO, Long> {
}