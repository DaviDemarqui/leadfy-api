package com.discern.api.service;

import com.discern.api.dto.ClientDTO;
import com.discern.api.model.Client;
import com.discern.api.repository.ClientRepository;
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

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final MapperUtil mapperUtil;
    private final ClientRepository clientRepository;

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
        Client projectSave;

        if(id != null) {
            projectSave = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
            Client pDTO = mapperUtil.mapTo(clientDTO, Client.class);
            BeanUtils.copyProperties(pDTO, projectSave, "id");
        } else {
            projectSave = mapperUtil.mapTo(clientDTO, Client.class);
        }

        return mapperUtil.mapTo(clientRepository.save(projectSave), ClientDTO.class);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
