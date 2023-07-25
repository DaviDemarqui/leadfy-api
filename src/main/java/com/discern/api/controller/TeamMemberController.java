package com.discern.api.controller;

import com.discern.api.dto.NoteDTO;
import com.discern.api.dto.TeamMemberDTO;
import com.discern.api.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/team-members")
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_TEAM_MEMBER_READ')")
    public ResponseEntity<?> getAllTeamMembers(TeamMemberDTO teamDTO, Pageable pageable) {
//        tokenVerifyingService.validateCompanyId(token, teamDTO.getCompanyId());
        return ResponseEntity.ok(teamMemberService.getAllTeamMembers(teamDTO, pageable));
    }
}
