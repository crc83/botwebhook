package com.sbelei.botapi.viber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbelei.botapi.common.BotHandlerInterface;
import com.sbelei.botapi.viber.request.buttons.Button;
import com.sbelei.botapi.viber.request.buttons.Keyboard;
import com.sbelei.botapi.viber.request.buttons.KeyboardRequest;
import com.sbelei.botapi.viber.request.sentmessage.Sender;
import com.sbelei.botapi.viber.request.sentmessage.SentMessageRequest;
import com.sbelei.botapi.viber.request.setwebhook.EventType;
import com.sbelei.botapi.viber.request.setwebhook.SetWebhookRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViberBotHandler implements BotHandlerInterface {

    Logger LOG = LoggerFactory.getLogger(ViberBotHandler.class);

    @Autowired
    private ViberHttpClient viberHttpClient;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void sendMessage(String userId, String message) {
        SentMessageRequest sendMessage = new SentMessageRequest();
        sendMessage.receiver = userId;
        sendMessage.min_api_version = 1;
        sendMessage.sender = new Sender("schedulify.com.ua", "http://avatar.example.com");
        sendMessage.tracking_data = "NOP";
        sendMessage.type = "text";
        sendMessage.text = message;

        viberHttpClient.post("https://chatapi.viber.com/pa/send_message", toJson(sendMessage));
    }

    @Override
    public String getBotType() {
        return "viber";
    }

    @Override
    public String getPhoneNumber(Object input) {
        return null;
    }

    @Override
    public String getActionName(Object input) {
        return null;
    }

    @Override
    public void send4SchedulesKeyboard() {

    }

    @Override
    public void sendShareContactKeyboard(String userId) {
        Button button = new Button();
        button.ActionType = "share-phone";
        button.Text = "Надіслати мій номер";
        button.TextSize = "regular";

        KeyboardRequest keyboardRequest = new KeyboardRequest();
        keyboardRequest.keyboard = new Keyboard(button);
        keyboardRequest.receiver = userId;
        keyboardRequest.text = "Надішліть свій номер в форматі 38ХХХХХХХХХХ або натисніть 'Надіслати номер' ";

        viberHttpClient.post("https://chatapi.viber.com/pa/send_message", toJson(keyboardRequest));
    }

    public String setWebhook(String url) {

        SetWebhookRequest request = new SetWebhookRequest();
        request.url = url;
        request.event_types = new EventType[]{
                EventType.seen,
                EventType.conversation_started,
                EventType.delivered,
                EventType.failed,
                EventType.subscribed,
                EventType.unsubscribed
        };
        request.send_name = true;
        request.send_photo = true;

        return viberHttpClient.post("https://chatapi.viber.com/pa/set_webhook", toJson(request));
    }

    private String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            if (object != null) {
                LOG.error("Error processing json for object:" + object.toString(), e);
            } else {
                LOG.error("Error processing json null", e);
            }
        }
        return "";
    }
}
