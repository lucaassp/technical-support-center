package com.supportcenter.technicalsupportcenter.services;

import com.supportcenter.technicalsupportcenter.domains.Client;
import com.supportcenter.technicalsupportcenter.domains.Technician;
import com.supportcenter.technicalsupportcenter.domains.User;
import com.supportcenter.technicalsupportcenter.domains.dto.ClientDTO;
import com.supportcenter.technicalsupportcenter.repositories.ClientRepository;
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
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ClientDTO> findAll() {
        List<Client> listClients = clientRepository.findAll();
        return listClients.stream().map(t -> new ClientDTO(t)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Client findById(Long id) {
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado! Id: " + id));
    }

    private void validation(ClientDTO dto) {
        Optional<User> obj = userRepository.findByCpf(dto.getCpf());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DatabaseException("CPF registered in the system");
        }
        obj = userRepository.findByEmail(dto.getEmail());
        if (obj.isPresent() && obj.get().getEmail() != dto.getEmail()) {
            throw new DatabaseException("Email registered in the system");
        }
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client obj = new Client();
        validation(dto);
        obj.setName(dto.getName());
        obj.setCpf(dto.getCpf());
        obj.setEmail(dto.getEmail());
        obj.setPassword(dto.getPassword());
        obj = clientRepository.save(obj);

        return new ClientDTO(obj);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client obj = clientRepository.getReferenceById(id);
            obj.setName(dto.getName());
            obj.setPassword(dto.getPassword());

            return new ClientDTO(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " not found");
        }
    }

    public void delete(Long id) {
        findById(id);
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("There is a service call from the client");
        }
    }
}
