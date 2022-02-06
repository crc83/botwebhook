package com.sbelei.botapi.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Deprecated
@RestController
public class TelegramResource {

    private final Logger LOG = LoggerFactory.getLogger(TelegramResource.class);

    public ResponseEntity<String> setWebhook() {
//        SetWebhook request = new SetWebhook();
//        request.setUrl("https://bots.schedulify.com.ua/telegram/proxy");
//
//        LOG.info("Registring callback" + telegramBot.execute(request));
        return ResponseEntity.ok("ok");
    }

    public ResponseEntity<String> getTelegramBotStatus()  {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setText("Foobar");
//        sendMessage.setChatId("504734059");
//        Message message = telegramBot.execute(sendMessage);
        return ResponseEntity.ok("fubar");//message.getText());
    }

    public ResponseEntity<String> proxy(@RequestBody Optional<Object> requestBodyOptional) {
        if (requestBodyOptional.isPresent()) {
            LOG.info("incoming request:" + requestBodyOptional.get().toString());
        }
        return ResponseEntity.ok("ok");
    }
}
