package com.sbelei.botwebhooks.rest;

import com.sbelei.botwebhooks.rest.viber.request.IncomingMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/viber")
public class ViberWebHookController {
    private static final Logger LOG = LoggerFactory.getLogger(ViberWebHookController.class);
    private static Map<String, String> knownUsers = new HashMap<>();
    private static Set<String> usersWeWaitForPhoneNumber = new HashSet<>();

    @GetMapping("/reregister")
    public ResponseEntity<String> reRegister() {
        registerViberBot();
        return ResponseEntity.ok("registred");
    }

    private void registerViberBot() {
        //https://chatapi.viber.com/pa/set_webhook
        // TODO : Add lib for HTTP request (okHTTP)
        // TODO : Add library for JSONs
//        """
//                            {
//                               "url":"https://bots.schedulify.com.ua/viber/receive",
//                               "event_types":[
//                                  "delivered",
//                                  "seen",
//                                  "failed",
//                                  "subscribed",
//                                  "unsubscribed",
//                                  "conversation_started"
//                               ],
//                               "send_name": true,
//                               "send_photo": true
//                            }
//"""
    }

    @PostMapping("/receive")
    public ResponseEntity<String> handleIncomingMessage(@RequestBody IncomingMessageRequest incomingMessage)  {
        LOG.info("Received incoming message:"+incomingMessage.toString());
        String userId = incomingMessage.getSender().getId();
        if (isUserKnown(userId)) {
            //then handle regular conversation via commands
            sendMessage( userId, "Your phone number is :"+ knownUsers.get(userId));
        } else if (isUserAskedForPhoneNumber(userId)) {
            if (isPhoneNumberValid(incomingMessage.getMessage().getText())) {
                saveUserPhoneNumber(userId, incomingMessage.getMessage().getText());
            } else {
                sendMessage( userId, "Phone number is invalid, please try again");
            }
        } else {
            sendMessage(userId, "Nice to meet you first time here");
            sendMessage(userId, "To get most of the platform, please sent us your phone number you use during registration in a next message");
            setUserAskedForPhoneNumber(userId);
        }
        return ResponseEntity.ok("Incoming message parsed");
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

    private void sendMessage(String userId, String message) {
    }

    private boolean isUserKnown(String viberUserId) {
        return knownUsers.containsKey(viberUserId);
    }

    private boolean isPhoneNumberValid(String possiblePhoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(possiblePhoneNumber);
        return matcher.matches();
    }
    //Viber requires that we handle any request here and always return 200
//    @RequestMapping("/receive")
//    public ResponseEntity<String> receive() {
//        //lets parse request to see if we can get
//        return ResponseEntity.ok("Hello world");
//    }
}
