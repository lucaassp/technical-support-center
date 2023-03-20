package com.supportcenter.technicalsupportcenter.resources;

import com.supportcenter.technicalsupportcenter.domains.Client;
import com.supportcenter.technicalsupportcenter.domains.dto.ClientDTO;
import com.supportcenter.technicalsupportcenter.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
    @Autowired
    private ClientService clientService;

    @GetMapping // Responds to a Get resource from http
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<ClientDTO> listTechnician = clientService.findAll();
        return ResponseEntity.ok().body(listTechnician);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        Client obj = clientService.findById(id);
        return ResponseEntity.ok().body(new ClientDTO(obj));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO dto) {
        dto = clientService.insert(dto);
        URI uri = ServletUriComponentsBuilder //
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto) {
        dto = clientService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
