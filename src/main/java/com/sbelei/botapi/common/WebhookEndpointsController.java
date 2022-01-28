package com.sbelei.botapi.common;

import com.sbelei.botapi.telegram.TelegramBotHandler;
import com.sbelei.botapi.viber.ViberBotHandler;
import com.sbelei.botapi.viber.request.incomingmessage.IncomingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

@RestController
@RequestMapping("/${webhook_base}")
public class WebhookEndpointsController {

    @Autowired
    private ViberBotHandler viberBot;

    @Autowired
    private TelegramBotHandler telegramBot;

    private static final Logger LOG = LoggerFactory.getLogger(WebhookEndpointsController.class);

    private Queue<IncomingEvent> viberQueue = new LinkedList<IncomingEvent>();

    @PostConstruct
    public void init() {
        viberBot.setWebhook("https://bots.schedulify.com/${webhook_base}/viber");
        telegramBot.setWebhook("https://bots.schedulify.com.ua/${webhook_base}/telegram");
    }

    @RequestMapping("/viber")
    public ResponseEntity<String> viber(@RequestBody Optional<IncomingEvent> incomingMessageOptional) {
        if (incomingMessageOptional.isEmpty()) {
            LOG.debug("No incoming content");
            return ResponseEntity.ok("No incoming content");
        }

        IncomingEvent incomingMessage = incomingMessageOptional.get();
        viberQueue.add(incomingMessage);
        return ResponseEntity.ok("saved");
    }

    @GetMapping("/initviber")
    public ResponseEntity<String> setWebhookViber() {
        return ResponseEntity.ok(viberBot.setWebhook("https://bots.schedulify.com/${webhook_base}/viber"));
    }

    @GetMapping("/inittelegram")
    public ResponseEntity<String> setWebhookTelegram() throws TelegramApiException {
        telegramBot.setWebhook("https://bots.schedulify.com.ua/${webhook_base}/telegram");
        return ResponseEntity.ok("ok");
    }

    @RequestMapping("/telegram")
    public ResponseEntity<String> telegram(@RequestBody Optional<Object> requestBodyOptional) {
        if (requestBodyOptional.isPresent()) {
            LOG.debug("incoming request:" + requestBodyOptional.get().toString());
        }
        return ResponseEntity.ok("ok");
    }
}