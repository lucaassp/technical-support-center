package com.supportcenter.technicalsupportcenter.repositories;

import com.supportcenter.technicalsupportcenter.domains.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {
}
