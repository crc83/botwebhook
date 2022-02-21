package com.sbelei.botapi.common;

import com.sbelei.botapi.telegram.TelegramHttpClient;
import com.sbelei.botapi.telegram.responce.getupdate.Update;
import com.sbelei.botapi.viber.ViberBotHandler;
import com.sbelei.botapi.viber.request.incomingmessage.IncomingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

@RestController
@RequestMapping("/${botapi.webhook_base}")
public class BotController {

    @Autowired
    private ViberBotHandler viberBot;

    @Autowired
    private TelegramHttpClient telegramBot;

    @Autowired
    private ConversationManager conversationManager;

    private static final Logger LOG = LoggerFactory.getLogger(BotController.class);

    private Queue<IncomingEvent> viberQueue = new LinkedList<IncomingEvent>();

    @Value( "${botapi.webhook_base}" )
    private String webhookBase;

    @PostConstruct
    public void init() {
    //    viberBot.setWebhook("https://bots.schedulify.com/"+webhookBase+"/viber");
    //    telegramBot.setWebhook("https://bots.schedulify.com.ua/"+webhookBase+"/telegram");
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
        return ResponseEntity.ok(viberBot.setWebhook("https://bots.schedulify.com/"+webhookBase+"/viber"));
    }

    @GetMapping("/inittelegram")
    public ResponseEntity<String> setWebhookTelegram() {
        telegramBot.setWebhook("https://bots.schedulify.com.ua/"+webhookBase+"/telegram");
        return ResponseEntity.ok("ok");
    }

    @RequestMapping("/telegram")
    public ResponseEntity<String> telegram(@RequestBody Optional<Update> requestBodyOptional) {
        if (requestBodyOptional.isPresent()) {
            LOG.debug("incoming request:" + requestBodyOptional.get().toString());
            try {
                conversationManager.recieveTelegramMessage(requestBodyOptional.get());
            } catch (Exception e) {
                LOG.error("Error in telegram endpoint", e);
                return ResponseEntity.ok("error");
            }
        }

        return ResponseEntity.ok("ok");
    }
}
