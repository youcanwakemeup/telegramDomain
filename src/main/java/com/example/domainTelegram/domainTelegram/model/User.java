package com.example.domainTelegram.domainTelegram.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "telegram_chat_id", unique = true)
    private Long telegramChatId;

    @Column(name = "last_message_at")
    private LocalDateTime lastMessageAt;


}
