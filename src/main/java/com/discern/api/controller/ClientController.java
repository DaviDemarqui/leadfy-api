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
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CLIENT_READ')")
    public ResponseEntity<?> getAllClients(Pageable pageable) {
        return ResponseEntity.ok(clientService.getAllClients(pageable));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_CLIENT_READ')")
    public ResponseEntity<?> getAllClients(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CLIENT_CREATE')")
    public ResponseEntity<?> postClient(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.saveOrUpdate(clientDTO, null));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_CLIENT_UPDATE')")
    public ResponseEntity<?> putClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.saveOrUpdate(clientDTO, id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_CLIENT_DELETE')")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
