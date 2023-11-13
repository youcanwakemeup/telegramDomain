package com.example.domainTelegram.domainTelegram.handler;


import com.example.domainTelegram.domainTelegram.service.MessageService;
import com.example.domainTelegram.domainTelegram.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class BotHandler extends TelegramLongPollingBot  {

    private final UserService userService;
    private final MessageService messageService;
    private final String botToken;
    private final String username;

    public BotHandler(UserService userService, MessageService messageService, @Value("${telegram.bot.token}") String botToken, @Value("${telegram.bot.username}") String username)  {
        this.userService = userService;
        this.messageService = messageService;
        this.botToken = botToken;
        this.username = username;
    }


    private static LocalDateTime convertMessageGetDateToLocalDateTime(Integer MessageGetDate) {
        Instant instant = Instant.ofEpochSecond(MessageGetDate);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String lastMessageText = update.getMessage().getText();
        Integer MessageGetDate = update.getMessage().getDate();
        LocalDateTime lastMessageAt = convertMessageGetDateToLocalDateTime(MessageGetDate);
        userService.addUser(chatId, lastMessageAt);
        messageService.addMessage(userService.findUser(chatId), lastMessageText, lastMessageAt);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
