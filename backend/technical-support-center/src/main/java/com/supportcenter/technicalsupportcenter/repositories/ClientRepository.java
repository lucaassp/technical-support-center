package com.supportcenter.technicalsupportcenter.repositories;

import com.supportcenter.technicalsupportcenter.domains.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
