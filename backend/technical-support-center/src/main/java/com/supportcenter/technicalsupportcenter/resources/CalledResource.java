package com.supportcenter.technicalsupportcenter.resources;

import com.supportcenter.technicalsupportcenter.domains.Called;
import com.supportcenter.technicalsupportcenter.domains.dto.CalledDTO;
import com.supportcenter.technicalsupportcenter.domains.dto.ClientDTO;
import com.supportcenter.technicalsupportcenter.services.CalledService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/calleds")
public class CalledResource {

    @Autowired
    private CalledService calledService;

    @GetMapping // Responds to a Get resource from http
    public ResponseEntity<List<CalledDTO>> findAll() {
        List<CalledDTO> listCalleds = calledService.findAll();
        return ResponseEntity.ok().body(listCalleds);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CalledDTO> findById(@PathVariable Long id) {
        CalledDTO calledId = calledService.findById(id);
        return ResponseEntity.ok().body(calledId);
    }

    @PostMapping
    public ResponseEntity<CalledDTO> insert(@Valid @RequestBody CalledDTO obj) {
        Called newObj = calledService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CalledDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO dto) {
        dto = calledService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
