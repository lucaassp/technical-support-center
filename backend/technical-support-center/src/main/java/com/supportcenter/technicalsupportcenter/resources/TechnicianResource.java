package com.supportcenter.technicalsupportcenter.resources;

import com.supportcenter.technicalsupportcenter.domains.Technician;
import com.supportcenter.technicalsupportcenter.domains.dto.TechnicianDTO;
import com.supportcenter.technicalsupportcenter.services.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/technicians")
public class TechnicianResource {
    @Autowired
    private TechnicianService technicianService;

    @GetMapping // Responds to a Get resource from http
    public ResponseEntity<List<TechnicianDTO>> findAll() {
        List<TechnicianDTO> listTechnician = technicianService.findAll();
        return ResponseEntity.ok().body(listTechnician);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Long id) {
        Technician obj = technicianService.findById(id);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @PostMapping
    public ResponseEntity<TechnicianDTO> insert(@Valid @RequestBody TechnicianDTO dto) {
        dto = technicianService.insert(dto);
        URI uri = ServletUriComponentsBuilder //
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable Long id, @RequestBody TechnicianDTO dto) {
        dto = technicianService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        technicianService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
