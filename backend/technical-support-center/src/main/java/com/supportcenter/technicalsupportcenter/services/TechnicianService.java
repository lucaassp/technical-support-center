package com.supportcenter.technicalsupportcenter.services;

import com.supportcenter.technicalsupportcenter.domains.Technician;
import com.supportcenter.technicalsupportcenter.domains.User;
import com.supportcenter.technicalsupportcenter.domains.dto.TechnicianDTO;
import com.supportcenter.technicalsupportcenter.repositories.TechnicianRepository;
import com.supportcenter.technicalsupportcenter.repositories.UserRepository;
import com.supportcenter.technicalsupportcenter.services.exceptions.DatabaseException;
import com.supportcenter.technicalsupportcenter.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TechnicianService {
    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<TechnicianDTO> findAll() {
        List<Technician> listTechnician = technicianRepository.findAll();
        return listTechnician.stream().map(t -> new TechnicianDTO(t)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Technician findById(Long id) {
        Optional<Technician> obj = technicianRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado! Id: " + id));
    }

    private void validation(TechnicianDTO dto) {
        Optional<User> obj = userRepository.findByCpf(dto.getCpf());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw  new DatabaseException("CPF registered in the system");
        }
        obj = userRepository.findByEmail(dto.getEmail());
        if (obj.isPresent() && obj.get().getEmail() != dto.getEmail()) {
            throw  new DatabaseException("Email registered in the system");
        }
    }

    @Transactional
    public TechnicianDTO insert(TechnicianDTO dto) {
        Technician obj = new Technician();
        validation(dto);
        obj.setName(dto.getName());
        obj.setCpf(dto.getCpf());
        obj.setEmail(dto.getEmail());
        obj.setPassword(dto.getPassword());
        obj = technicianRepository.save(obj);

        return new TechnicianDTO(obj);
    }

    @Transactional
    public TechnicianDTO update(Long id, TechnicianDTO dto) {
        try {
            Technician obj = technicianRepository.getReferenceById(id);
            obj.setName(dto.getName());
            obj.setPassword(dto.getPassword());

            return new TechnicianDTO(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " not found");
        }
    }

    public void delete(Long id) {
        findById(id);
        try {
            technicianRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("There is a work order for this technician");
        }
    }
}
