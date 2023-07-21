package com.discern.api.controller;

import com.discern.api.dto.ClientDTO;
import com.discern.api.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/clients")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ClientController {

    private final ClientService clientService;

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
