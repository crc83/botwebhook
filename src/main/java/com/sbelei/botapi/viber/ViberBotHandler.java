package com.sbelei.botapi.viber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbelei.botapi.common.BotHandlerInterface;
import com.sbelei.botapi.viber.request.buttons.Button;
import com.sbelei.botapi.viber.request.buttons.Keyboard;
import com.sbelei.botapi.viber.request.buttons.KeyboardRequest;
import com.sbelei.botapi.viber.request.incomingmessage.IncomingEvent;

import java.io.IOException;

public class ViberBotHandler implements BotHandlerInterface {



    private ObjectMapper mapper = new ObjectMapper();
    
    public void respondToEvent(ViberHttpClient client, IncomingEvent event) throws IOException {
        runAny(client, event);
    }


    public void startConversation(ViberHttpClient client, IncomingEvent event) throws IOException {
        runAny(client, event);
    }

    public void runAny(ViberHttpClient client, IncomingEvent event) throws IOException {
        //case state
        askPhoneNumber(client, event);
    }

    private void askPhoneNumber(ViberHttpClient client, IncomingEvent event) throws IOException {
        Button button = new Button();
        button.ActionType = "share-phone";
        button.Text = "Надіслати мій номер";
        button.TextSize = "regular";

        KeyboardRequest keyboardRequest = new KeyboardRequest();
        keyboardRequest.keyboard = new Keyboard(button);
        keyboardRequest.receiver = event.sender.id;
        keyboardRequest.text = "Надішліть свій номер в форматі 38ХХХХХХХХХХ або натисніть 'Надіслати номер' ";

        client.send_message( mapper.writeValueAsString(keyboardRequest));
    }
}
