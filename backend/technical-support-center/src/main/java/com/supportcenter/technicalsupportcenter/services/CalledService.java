package com.supportcenter.technicalsupportcenter.services;

import com.supportcenter.technicalsupportcenter.domains.Called;
import com.supportcenter.technicalsupportcenter.domains.Client;
import com.supportcenter.technicalsupportcenter.domains.Technician;
import com.supportcenter.technicalsupportcenter.domains.dto.CalledDTO;
import com.supportcenter.technicalsupportcenter.domains.dto.ClientDTO;
import com.supportcenter.technicalsupportcenter.domains.enums.Priority;
import com.supportcenter.technicalsupportcenter.domains.enums.Status;
import com.supportcenter.technicalsupportcenter.repositories.CalledRepository;
import com.supportcenter.technicalsupportcenter.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalledService {
    @Autowired
    private CalledRepository calledRepository;
    @Autowired
    private TechnicianService technicianService;
    @Autowired
    private ClientService clientService;

    @Transactional(readOnly = true)
    public List<CalledDTO> findAll() {
        List<Called> listClients = calledRepository.findAll();
        return listClients.stream().map(c -> new CalledDTO(c)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CalledDTO findById(Long id) {
        return calledRepository.findById(id).map(c -> new CalledDTO(c)).orElseThrow(() -> new ResourceNotFoundException("Id " + id + " not found"));
    }

    private Called newCalled(CalledDTO obj) {
        Technician technician = technicianService.findById(obj.getTechnician());
        Client client = clientService.findById(obj.getClient());


        Called called = new Called();
        if(obj.getId() != null) {
            called.setId(obj.getId());
        }
        if (obj.getStatus().equals(2)){
            called.setClosingDate(LocalDate.now());
        }

        called.setTechnician(technician);
        called.setClient(client);
        called.setPriority(Priority.valueOf(obj.getPriority()));
        called.setStatus(Status.valueOf(obj.getStatus()));
        called.setTitle(obj.getTitle());
        called.setObservation(obj.getObservation());

        return called;
    }
    @Transactional
    public Called insert(CalledDTO dto) {
        return calledRepository.save(newCalled(dto));
    }
}
