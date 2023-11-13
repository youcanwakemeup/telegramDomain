package com.example.domainTelegram.domainTelegram.service;

import com.example.domainTelegram.domainTelegram.model.User;
import com.example.domainTelegram.domainTelegram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Long> getAllIds() {
        List<User> users = userRepository.findAll();
        List<Long> ids = new ArrayList<>();
        for (User user: users) {
            ids.add(user.getTelegramChatId());
        }
        return ids;
    }

    @Transactional
    public void addUser(Long chatId, LocalDateTime lastMessageAt) {
        User existingUser = userRepository.findByTelegramChatId(chatId);

        if (existingUser != null) {
            existingUser.setLastMessageAt(lastMessageAt);
            userRepository.save(existingUser);
        } else {
            userRepository.save(new User(chatId, lastMessageAt));
        }
    }

    public User findUser(Long chatId) {
        return userRepository.findByTelegramChatId(chatId);
    }
}
