package com.sbelei.botapi.viber;

import com.sbelei.botapi.viber.request.incomingmessage.IncomingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/viber")
public class ViberBotResource {

    private Queue<IncomingEvent> queue = new LinkedList<IncomingEvent>();

    @Autowired
    private ViberBot viberBot;

    private static final Logger LOG = LoggerFactory.getLogger(ViberBotResource.class);
    private static Map<String, String> knownUsers = new HashMap<>();
    private static Set<String> usersWeWaitForPhoneNumber = new HashSet<>();

    @Autowired
    private ViberHttpClient viberHttpClient;

    @GetMapping("/reregister")
    public ResponseEntity<String> reRegister() {
        String response;
        try {
            response = registerViberBot();
        } catch (Exception e) {
            e.printStackTrace();
            response = "Error";
        }
        return ResponseEntity.ok(response);
    }

    private String registerViberBot() throws IOException {
        return viberHttpClient.post("https://chatapi.viber.com/pa/set_webhook",
                viberBot.setWebhookUrl("https://bots.schedulify.com.ua/viber/proxy"));
//                viberBot.setWebhookUrl("https://bots.schedulify.com.ua/viber/receive"));
    }


    @RequestMapping("/proxy")
    public ResponseEntity<String> proxy(@RequestBody Optional<IncomingEvent> incomingMessageOptional) {
        if (incomingMessageOptional.isEmpty()) {
            LOG.info("No incoming content");
            return ResponseEntity.ok("No incoming content");
        }

        IncomingEvent incomingMessage = incomingMessageOptional.get();
        queue.add(incomingMessage);
        return ResponseEntity.ok("saved");
    }

    @GetMapping("/getevent")
    public ResponseEntity<IncomingEvent> getEvent() {
        if (queue.isEmpty()) {
            return ResponseEntity.of(Optional.empty());
        } else {
            return ResponseEntity.of(Optional.of(queue.poll()));
        }
    }

    @PostMapping("/send_message")
    public ResponseEntity<String> sentMessage(@RequestBody String body) throws IOException {
        return ResponseEntity.ok(viberHttpClient.post("https://chatapi.viber.com/pa/send_message", body));
    }
    //rewrite it
    @RequestMapping("/receive")
    public ResponseEntity<String> handleIncomingMessage(@RequestBody Optional<IncomingEvent> incomingMessageOptional)  {
        try {
            if (incomingMessageOptional.isEmpty()) {
                LOG.info("No incoming content");
                return ResponseEntity.ok("No incoming content");
            }

            IncomingEvent incomingMessage = incomingMessageOptional.get();

            LOG.info("Received incoming event:" + incomingMessage.toString());
            if (!"message".equals(incomingMessage.getEvent())) {
                return ResponseEntity.ok("Incoming event ignored:" + incomingMessage.getEvent());
            }
            String userId = incomingMessage.getSender().getId();
            if (isUserKnown(userId)) {
                //then handle regular conversation via commands
                sendMessage(userId, "Your phone number is :" + knownUsers.get(userId));
            } else if (isUserAskedForPhoneNumber(userId)) {
                if (isPhoneNumberValid(incomingMessage.getMessage().getText())) {
                    saveUserPhoneNumber(userId, incomingMessage.getMessage().getText());
                } else {
                    sendMessage(userId, "Phone number is invalid, please try again");
                }
            } else {
                sendMessage(userId, "Nice to meet you first time here");
                sendMessage(userId, "To get most of the platform, please sent us your phone number you use during registration in a next message");
                setUserAskedForPhoneNumber(userId);
            }
            return ResponseEntity.ok("Incoming message parsed");
        } catch (Exception e) {
            LOG.error("Unexpected error", e);
            return ResponseEntity.ok("Error:" + e.getMessage());
        }
    }

    private void saveUserPhoneNumber(String userId, String phoneNumber) {
        knownUsers.put(userId, phoneNumber);
    }

    private void setUserAskedForPhoneNumber(String userId) {
        usersWeWaitForPhoneNumber.add(userId);
    }

    private boolean isUserAskedForPhoneNumber(String userId) {
        return usersWeWaitForPhoneNumber.contains(userId);
    }

    private void sendMessage(String userId, String message) throws IOException {
        viberHttpClient.post("https://chatapi.viber.com/pa/send_message",viberBot.sendTextMessage(userId, message));
    }

    private boolean isUserKnown(String viberUserId) {
        return knownUsers.containsKey(viberUserId);
    }

    private boolean isPhoneNumberValid(String possiblePhoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(possiblePhoneNumber);
        return matcher.matches();
    }

    @RequestMapping("/defaultrecieve")
    public ResponseEntity<String> receive() {
        //lets parse request to see if we can get
        return ResponseEntity.ok("ok");
    }
}
