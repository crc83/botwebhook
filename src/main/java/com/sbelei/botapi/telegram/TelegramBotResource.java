package com.sbelei.botapi.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@RestController
@RequestMapping("/telegram")
public class TelegramBotResource {

    //https://github.com/pengrad/java-telegram-bot-api

    private final Logger LOG = LoggerFactory.getLogger(TelegramBotResource.class);

    @Autowired
    SchedulifyTelegramBot telegramBot;

    @GetMapping("/init")
    public ResponseEntity<String> setWebhook() throws TelegramApiException {
        SetWebhook request = new SetWebhook();
        request.setUrl("https://bots.schedulify.com.ua/telegram/proxy");

        LOG.info("Registring callback" + telegramBot.execute(request));
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/getstatus")
    public ResponseEntity<String> getTelegramBotStatus() throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Foobar");
        Message message = telegramBot.execute(sendMessage);
        return ResponseEntity.ok(message.getText());
    }

    @RequestMapping("/proxy")
    public ResponseEntity<String> proxy(@RequestBody Optional<Object> requestBodyOptional) {
        if (requestBodyOptional.isPresent()) {
            LOG.info("incoming request:" + requestBodyOptional.get().toString());
        }
        return ResponseEntity.ok("ok");
    }
}
