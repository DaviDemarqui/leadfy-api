package com.discern.api.service;

import com.discern.api.dto.ProjectDTO;
import com.discern.api.exceptions.ProjectNotFoundException;
import com.discern.api.model.Project;
import com.discern.api.repository.ProjectRepository;
import com.discern.api.utils.mapper.MapperUtil;
import com.discern.api.utils.matcher.TiposExampleMatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {

    private final MapperUtil mapperUtil;
    private final ProjectRepository projectRepository;

    public Page<ProjectDTO> getAllProjects(ProjectDTO projectDTO, Pageable pageable) {
        var example = Example.of(mapperUtil.mapTo(projectDTO, Project.class),
                TiposExampleMatcher.exampleMatcherMatchingAny());

        return projectRepository.findAll(example, pageable)
                .map(Project -> mapperUtil.mapTo(Project, ProjectDTO.class));
    }

    public ProjectDTO findById(Long id) {
        return mapperUtil.mapTo(projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new), ProjectDTO.class);
    }

    public ProjectDTO saveOrUpdate(ProjectDTO projectDTO, Long id) {
        Project projectSave;

        if(id != null) {
            projectSave = projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
            Project pDTO = mapperUtil.mapTo(projectDTO, Project.class);
            BeanUtils.copyProperties(pDTO, projectSave, "id");
        } else {
            projectSave = mapperUtil.mapTo(projectDTO, Project.class);
        }

        return mapperUtil.mapTo(projectRepository.save(projectSave), ProjectDTO.class);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
