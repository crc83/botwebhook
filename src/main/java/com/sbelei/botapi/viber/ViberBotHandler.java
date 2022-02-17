package com.sbelei.botapi.viber;

import com.sbelei.botapi.common.BotHandlerInterface;
import com.sbelei.botapi.common.CommonObjectMapper;
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

    private CommonObjectMapper mapper = new CommonObjectMapper();

    @Override
    public void sendMessage(String userId, String message) {
        SentMessageRequest sendMessage = new SentMessageRequest();
        sendMessage.receiver = userId;
        sendMessage.min_api_version = 1;
        sendMessage.sender = new Sender("schedulify.com.ua", "http://avatar.example.com");
        sendMessage.tracking_data = "NOP";
        sendMessage.type = "text";
        sendMessage.text = message;

        viberHttpClient.post("https://chatapi.viber.com/pa/send_message", mapper.toJson(sendMessage));
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
    public void sendShareContactKeyboard(String userId, String message, String shareContactButtonCaption) {
        Button button = new Button();
        button.ActionType = "share-phone";
        button.Text = shareContactButtonCaption;
        button.TextSize = "regular";

        KeyboardRequest keyboardRequest = new KeyboardRequest();
        keyboardRequest.keyboard = new Keyboard(button);
        keyboardRequest.receiver = userId;
        keyboardRequest.text = message;

        viberHttpClient.post("https://chatapi.viber.com/pa/send_message", mapper.toJson(keyboardRequest));
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

        return viberHttpClient.post("https://chatapi.viber.com/pa/set_webhook", mapper.toJson(request));
    }

}
