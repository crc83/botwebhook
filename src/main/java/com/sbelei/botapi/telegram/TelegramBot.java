package com.sbelei.botapi.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final Logger log = LoggerFactory.getLogger(TelegramBot.class);

    @Value("${telegrambot.name}")
    private String botName;
    @Value("${telegrambot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @PostConstruct
    public void init() {
        try {
            log.info("Telegram component loaded :" + getMe().getUserName());
        } catch (TelegramApiException e) {
            log.error("Telegram bot is not initialized properly. Telegram bot name [" +botName +"]" , e);
        }

    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Come update");
//        try {
//            SendMessage sendMessage = new SendMessage();
//            sendMessage.setChatId(Long.toString(update.getMessage().getChatId()));
//            sendMessage.setText("hi");
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            log.error(e.getMessage(), e);
//        }
    }
}
