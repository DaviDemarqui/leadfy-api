package com.discern.api.service;

import com.discern.api.dto.ProjectCreationDTO;
import com.discern.api.dto.ProjectDTO;
import com.discern.api.exceptions.ProjectNotFoundException;
import com.discern.api.model.Project;
import com.discern.api.model.Team;
import com.discern.api.model.TeamMember;
import com.discern.api.repository.*;
import com.discern.api.security.JwtAuthenticationFilter;
import com.discern.api.security.JwtGenerator;
import com.discern.api.utils.CompanyValidator;
import com.discern.api.utils.mapper.MapperUtil;
import com.discern.api.utils.matcher.TiposExampleMatcher;
import com.discern.api.view.ListProjectVO;
import com.discern.api.view.ProjectInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {

    private final MapperUtil mapperUtil;
    private final JwtGenerator jwtGenerator;
    private final ProjectRepository projectRepository;
    private final ListProjectVORepository listProjectVORepository;
    private final ProjectInfoVORepository projectInfoVORepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;


public Page<ListProjectVO> getAllProjects(Pageable pageable) {

        return listProjectVORepository.findAllByCompanyId(JwtAuthenticationFilter.getCurrentCompanyId(), pageable)
                .map(ProjectVO -> mapperUtil.mapTo(ProjectVO, ListProjectVO.class));
    }

    public ProjectDTO findById(Long id) {
        return mapperUtil.mapTo(projectRepository.findByIdAndCompanyId(id, JwtAuthenticationFilter.getCurrentCompanyId())
                .orElseThrow(ProjectNotFoundException::new), ProjectDTO.class);
    }

    public ProjectInfoVO findProjectInfoById(Long id, String token) {

        return projectInfoVORepository.findByProjectIdAndCompanyId(id, JwtAuthenticationFilter.getCurrentCompanyId())
                .orElseThrow(ProjectNotFoundException::new);
    }

    public ProjectDTO updateProject(ProjectDTO projectDTO, Long id) {

        CompanyValidator.validateId(projectDTO.getCompanyId());

        Project pDTO = mapperUtil.mapTo(projectDTO, Project.class);
        BeanUtils.copyProperties(pDTO, projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new), "id");
        return mapperUtil.mapTo(projectRepository.save(mapperUtil.mapTo(pDTO, Project.class)), ProjectDTO.class);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findByIdAndCompanyId(id, JwtAuthenticationFilter.getCurrentCompanyId())
                        .orElseThrow(ProjectNotFoundException::new);
        projectRepository.deleteById(project.getId());
    }

    public ProjectDTO createProject(ProjectDTO projectDTO, String token) {

        CompanyValidator.validateId(projectDTO.getCompanyId());

        Project saveProject = mapperUtil.mapTo(projectDTO, Project.class);
        projectRepository.save(saveProject);

        // Creating the team for the project
        Team team = new Team(null, saveProject.getName()+" Team", saveProject.getId(), saveProject.getCompanyId());
        teamRepository.save(team);

        TeamMember teamMember = new TeamMember(jwtGenerator.getUserIdFromToken(token), team.getId(), null, null);
        teamMemberRepository.save(teamMember);

        return mapperUtil.mapTo(saveProject, ProjectDTO.class);
    }
}
