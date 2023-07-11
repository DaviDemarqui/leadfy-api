package com.discern.api.service;

import com.discern.api.dto.ClientDTO;
import com.discern.api.model.Client;
import com.discern.api.model.Project;
import com.discern.api.repository.ClientRepository;
import com.discern.api.repository.ProjectRepository;
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
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final MapperUtil mapperUtil;
    private final ClientRepository clientRepository;
    private final ProjectRepository projectRepository;

    public Page<ClientDTO> getAllClients(ClientDTO clientDTO, Pageable pageable) {
        var example = Example.of(mapperUtil.mapTo(clientDTO, Client.class),
                TiposExampleMatcher.exampleMatcherMatchingAny());

        return clientRepository.findAll(example, pageable)
                .map(Client -> mapperUtil.mapTo(Client, ClientDTO.class));
    }

    public ClientDTO findById(Long id) {
        return mapperUtil.mapTo(clientRepository.findById(id)
                .orElseThrow(ClientNotFoundException::new), ClientDTO.class);
    }

    public ClientDTO saveOrUpdate(ClientDTO clientDTO, Long id) {
        Client clientSave = new Client();

        if(id != null) {
            if(clientDTO.getProjectId().equals(null)) {
                throw new RuntimeException("ERROR: No projectId provided");
            }
            clientSave = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
            Client pDTO = mapperUtil.mapTo(clientDTO, Client.class);
            BeanUtils.copyProperties(pDTO, clientSave, "id");
        } else {
            //Creating the project for the client
            Project project = new Project();
            project.setCompanyId(clientDTO.getCompanyId());
            project.setStatus(true);
            project.setCreatedOn(LocalDateTime.now());
            project.setName(clientDTO.getName());
            project.setOngoing(true);
            project.setDueDate(LocalDateTime.now().plusWeeks(1));

            projectRepository.save(project);
            clientSave = mapperUtil.mapTo(clientDTO, Client.class);
            clientSave.setProjectId(project.getId());
        };
        return mapperUtil.mapTo(clientRepository.save(clientSave), ClientDTO.class);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
