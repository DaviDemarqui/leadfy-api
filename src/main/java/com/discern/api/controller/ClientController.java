package com.discern.api.controller;

import com.discern.api.dto.ClientDTO;
import com.discern.api.service.ClientService;
import com.discern.api.service.TokenVerifyingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientService clientService;
    private final TokenVerifyingService tokenVerifyingService;
    // TODO - Ajustar company_id para seguir com a validação!

    @GetMapping
    public ResponseEntity<?> getAllClients(ClientDTO clientDTO, Pageable pageable) {
//        tokenVerifyingService.validateCompanyId(token, clientDTO.getCompanyId());
        return ResponseEntity.ok(clientService.getAllClients(clientDTO, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAllClients(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> postClient(@RequestBody ClientDTO clientDTO, @RequestHeader("Authorization") String token) {
        tokenVerifyingService.validateCompanyId(token, clientDTO.getCompanyId());
        return ResponseEntity.ok(clientService.saveOrUpdate(clientDTO, null));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.saveOrUpdate(clientDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
