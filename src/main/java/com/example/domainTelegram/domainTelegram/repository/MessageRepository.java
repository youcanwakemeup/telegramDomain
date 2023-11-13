package com.example.domainTelegram.domainTelegram.repository;

import com.example.domainTelegram.domainTelegram.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
