package com.discern.api.service;

import com.discern.api.dto.TeamDTO;
import com.discern.api.exceptions.TeamNotFoundException;
import com.discern.api.model.Team;
import com.discern.api.repository.TeamRepository;
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
public class TeamService {

    private final MapperUtil mapperUtil;
    private final TeamRepository companyRepository;

    public Page<TeamDTO> getAllTeams(TeamDTO teamDTO, Pageable pageable) {
        var example = Example.of(mapperUtil.mapTo(teamDTO, Team.class),
                TiposExampleMatcher.exampleMatcherMatchingAny());

        return companyRepository.findAll(example, pageable)
                .map(Team -> mapperUtil.mapTo(Team, TeamDTO.class));
    }

    public TeamDTO findById(Long id) {
        return mapperUtil.mapTo(companyRepository.findById(id)
                .orElseThrow(TeamNotFoundException::new), TeamDTO.class);
    }

    public TeamDTO saveOrUpdate(TeamDTO teamDTO, Long id) {
        Team teamSave;

        if(id != null) {
            teamSave = companyRepository.findById(id).orElseThrow(TeamNotFoundException::new);
            Team pDTO = mapperUtil.mapTo(teamDTO, Team.class);
            BeanUtils.copyProperties(pDTO, teamSave, "id");
        } else {
            teamSave = mapperUtil.mapTo(teamDTO, Team.class);
        }

        return mapperUtil.mapTo(companyRepository.save(teamSave), TeamDTO.class);
    }

    public void deleteTeam(Long id) {
        companyRepository.deleteById(id);
    }
}
