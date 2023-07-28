package com.discern.api.repository;

import com.discern.api.model.Project;
import com.discern.api.view.ProjectInfoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectInfoVORepository extends JpaRepository<ProjectInfoVO, Long> {
    Optional<ProjectInfoVO> findByIdAndCompanyId(Long id, Long companyId);

}