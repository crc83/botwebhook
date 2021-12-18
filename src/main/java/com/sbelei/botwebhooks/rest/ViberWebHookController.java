package com.sbelei.botwebhooks.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbelei.botwebhooks.rest.viber.request.message.IncomingMessageRequest;
import com.sbelei.botwebhooks.rest.viber.request.setwebhook.EventType;
import com.sbelei.botwebhooks.rest.viber.request.setwebhook.SetWebhookRequest;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

    @Value("${viber.bot_token}")
    private String viberBotTokenId;

    @GetMapping("/reregister")
    public ResponseEntity<String> reRegister() {
        String response = "";
        try {
            response = registerViberBot();
        } catch (Exception e) {
            e.printStackTrace();
            response = "Error";
        }
        return ResponseEntity.ok(response);
    }

    private String registerViberBot() throws IOException {
        SetWebhookRequest request =  new SetWebhookRequest();
        request.setUrl("https://bots.schedulify.com.ua/viber/defaultrecieve");
        request.setEvent_types( new EventType[] {
                EventType.SEEN,
                EventType.CONVERSATION_STARTED,
                EventType.DELIVERED,
                EventType.FAILED,
                EventType.SUBSCRIBED,
                EventType.UNSUBSCRIBED
        });
        request.setSend_name(true);
        request.setSend_photo(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);
        //https://chatapi.viber.com/pa/set_webhook

        //defaultrecieve
        return post("https://chatapi.viber.com/pa/set_webhook", json);
    }

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

    String post(String url, String json) throws IOException {
        okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Viber-Auth-Token",viberBotTokenId)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
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

    @RequestMapping("/defaultrecieve")
    public ResponseEntity<String> receive() {
        //lets parse request to see if we can get
        return ResponseEntity.ok("ok");
    }
}
