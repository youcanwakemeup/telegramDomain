package com.example.domainTelegram.domainTelegram.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.domainTelegram.domainTelegram.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByTelegramChatId(Long chatId);
    List<User> findAll();

}
