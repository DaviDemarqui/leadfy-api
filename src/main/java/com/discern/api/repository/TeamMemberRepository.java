package com.discern.api.repository;

import com.discern.api.model.Note;
import com.discern.api.model.TeamMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    Page<TeamMember> findAllByTeamProjectId(Long projectId, Pageable pageable);

}