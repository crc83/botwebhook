package com.sbelei.viberbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbelei.viberbot.request.buttons.Button;
import com.sbelei.viberbot.request.buttons.Keyboard;
import com.sbelei.viberbot.request.buttons.KeyboardRequest;
import com.sbelei.viberbot.request.incomingmessage.IncomingEvent;

import java.io.IOException;

public class ViberConversationState {

    enum ConversationStageEnum {
        START,
        PHONE_NUMBER_UNKNOWN,
        PHONE_NUMBER_INVALID,
        GET_ADDITIONAL_INFO,
        GET_SCHEDULE_FOR_TODAY,
        GET_SCHEDULE_FOR_TOMORROW,
        GET_SCHEDULE_FOR_THIS_WEEK,
        GET_SCHEDULE_FOR_NEXT_WEEK
    }

    private ConversationStageEnum state = ConversationStageEnum.START;

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
