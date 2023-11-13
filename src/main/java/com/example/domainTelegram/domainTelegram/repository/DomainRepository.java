package com.example.domainTelegram.domainTelegram.repository;

import com.example.domainTelegram.domainTelegram.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {

}
