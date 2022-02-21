package com.sbelei.botapi.common;

import com.sbelei.botapi.telegram.TelegramHttpClient;
import com.sbelei.botapi.telegram.responce.getupdate.Update;
import com.sbelei.botapi.viber.ViberBotHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.sbelei.botapi.common.ConversationManager.ConversationType.viber;

@Component
public class ConversationManager {

    private Logger LOG = LoggerFactory.getLogger(ConversationManager.class);

    enum ConversationType {
        viber,
        telegram
    }

    private TelegramHttpClient telegramBot;

    private ViberBotHandler viberBot;

    @Autowired
    public ConversationManager(ViberBotHandler viberBot, TelegramHttpClient telegramBot) {
        this.viberBot = viberBot;
        this.telegramBot = telegramBot;
    }

    Map<String, ConversationState> conversations = new HashMap<>();

    public void recieveViberMessage(String userId, Object input) throws Exception {
        String conversationId = viber+userId;
        if (!conversations.containsKey(conversationId)) {
            conversations.put(conversationId, new ConversationState(userId, newViberBotHandler()));
        }
        handleIncomingMessage(conversationId, input);

    }

    private ViberBotHandler newViberBotHandler() {
        return viberBot;
    }

    public void recieveTelegramMessage(Update input) throws Exception {
        String userId = input.message.from.id;
        String conversationId = ConversationType.telegram+userId;
        if (!conversations.containsKey(conversationId)) {
            conversations.put(conversationId, new ConversationState(userId, newTelegramBotHandler()));
        }
        handleIncomingMessage(conversationId, input);
    }

    private TelegramHttpClient newTelegramBotHandler() {
        return telegramBot;
    }

    public void handleIncomingMessage(String conversationId, Object input) throws Exception {
        ConversationState conversation = conversations.get(conversationId);
        conversation.accept(input);
    }
}
