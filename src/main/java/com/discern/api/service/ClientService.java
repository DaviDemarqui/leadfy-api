package com.discern.api.service;

import com.discern.api.dto.ClientDTO;
import com.discern.api.model.Client;
import com.discern.api.model.Project;
import com.discern.api.repository.ClientRepository;
import com.discern.api.repository.ProjectRepository;
import com.discern.api.security.JwtAuthenticationFilter;
import com.discern.api.utils.CompanyValidator;
import com.discern.api.utils.mapper.MapperUtil;
import com.discern.api.utils.matcher.TiposExampleMatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.discern.api.exceptions.ClientNotFoundException;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final MapperUtil mapperUtil;
    private final ClientRepository clientRepository;
    private final ProjectRepository projectRepository;

    public Page<ClientDTO> getAllClients(Pageable pageable) {

        return clientRepository.findAllByCompanyId(JwtAuthenticationFilter.getCurrentCompanyId(), pageable)
                .map(Client -> mapperUtil.mapTo(Client, ClientDTO.class));
    }

    public ClientDTO findById(Long id) {
        return mapperUtil.mapTo(clientRepository.findByIdAndCompanyId(id, JwtAuthenticationFilter.getCurrentCompanyId())
                .orElseThrow(ClientNotFoundException::new), ClientDTO.class);
    }

    public ClientDTO saveOrUpdate(ClientDTO clientDTO, Long id) {

        CompanyValidator.validateId(clientDTO.getCompanyId());
        Client clientSave;

        if(id != null) {
            if(clientDTO.getProjectId().equals(null)) {
                throw new RuntimeException("NULL Error - No project id provided!");
            }
            clientSave = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
            Client pDTO = mapperUtil.mapTo(clientDTO, Client.class);
            BeanUtils.copyProperties(pDTO, clientSave, "id");
        } else {
            //Creating the project for the client
            Project project = new Project();
            project.setCompanyId(clientDTO.getCompanyId());
            project.setStatus(true);
            project.setCreatedOn(LocalDate.now());
            project.setName(clientDTO.getName());
            project.setOngoing(true);
            project.setDueDate(LocalDate.now().plusWeeks(1));

            projectRepository.save(project);
            clientSave = mapperUtil.mapTo(clientDTO, Client.class);
            clientSave.setProjectId(project.getId());
        };
        return mapperUtil.mapTo(clientRepository.save(clientSave), ClientDTO.class);
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findByIdAndCompanyId(id, JwtAuthenticationFilter.getCurrentCompanyId())
                .orElseThrow(ClientNotFoundException::new);
        clientRepository.deleteById(client.getId());
    }
}
