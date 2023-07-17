package com.discern.api.repository;

import com.discern.api.view.ProjectInfoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectInfoVORepository extends JpaRepository<ProjectInfoVO, Long> {
}