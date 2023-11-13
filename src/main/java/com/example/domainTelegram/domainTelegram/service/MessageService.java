package com.example.domainTelegram.domainTelegram.service;

import com.example.domainTelegram.domainTelegram.model.Message;
import com.example.domainTelegram.domainTelegram.model.User;
import com.example.domainTelegram.domainTelegram.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void addMessage(User chatId, String messageText, LocalDateTime sentAt) {
        Message message = new Message(chatId, messageText, sentAt);
        messageRepository.save(message);
    }
}
