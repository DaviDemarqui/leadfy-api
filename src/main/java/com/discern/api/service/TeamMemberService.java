package com.discern.api.service;

import com.discern.api.dto.TeamMemberDTO;
import com.discern.api.model.Note;
import com.discern.api.model.TeamMember;
import com.discern.api.repository.TeamMemberRepository;
import com.discern.api.security.JwtAuthenticationFilter;
import com.discern.api.utils.mapper.MapperUtil;
import com.discern.api.utils.matcher.TiposExampleMatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final MapperUtil mapperUtil;

    public Page<TeamMemberDTO> getAllTeamMembers(Long projectId, Pageable pageable) {

        return teamMemberRepository.findAllByProjectIdAndCompanyId(JwtAuthenticationFilter.getCurrentCompanyId(), projectId, pageable)
                .map(Team -> mapperUtil.mapTo(Team, TeamMemberDTO.class));
    }

}
