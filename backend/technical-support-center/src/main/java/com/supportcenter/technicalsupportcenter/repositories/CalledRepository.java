package com.supportcenter.technicalsupportcenter.repositories;

import com.supportcenter.technicalsupportcenter.domains.Called;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalledRepository extends JpaRepository<Called, Long> {
}
