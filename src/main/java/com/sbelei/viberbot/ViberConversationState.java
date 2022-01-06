package com.sbelei.viberbot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbelei.viberbot.request.buttons.Button;
import com.sbelei.viberbot.request.buttons.Keyboard;
import com.sbelei.viberbot.request.buttons.KeyboardRequest;
import com.sbelei.viberbot.request.incomingmessage.IncomingEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ViberConversationState {

    private ObjectMapper mapper = new ObjectMapper();
    
    public void respondToEvent(ViberHttpClient client, IncomingEvent event) {
    }

    public void startConversation(ViberHttpClient client, IncomingEvent event) throws IOException {
        Button button = new Button();
        button.setActionType("reply");
        button.setActionBody("reply to me");
        button.setText("Key text");
        button.setTextSize("regular");

        Keyboard keyboard = new Keyboard(button);
        KeyboardRequest keyboardRequest = new KeyboardRequest();
        keyboardRequest.setKeyboard(keyboard);
        keyboardRequest.setReceiver(event.getSender().getId());
        keyboardRequest.setText("Foo bar");


        String json = mapper.writeValueAsString(keyboardRequest);
        client.post("https://chatapi.viber.com/pa/send_message", json);
    }
}
