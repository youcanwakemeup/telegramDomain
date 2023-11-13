package com.example.domainTelegram.domainTelegram.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "telegram_chat_id")
    private User user;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    public Message(User user, String messageText, LocalDateTime sentAt) {
        this.user = user;
        this.messageText = messageText;
        this.sentAt = sentAt;
    }
}
