package com.sbelei.botapi.common;

import com.sbelei.botapi.telegram.TelegramBotHandler;
import com.sbelei.botapi.viber.ViberBotHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ConversationManager {

    enum ConversationType {
        viber,
        telegram
    }

    Map<String, ConversationState> conversations = new HashMap<>();

    public void recieveViberMessage(String userId, Object input) {
        String conversationId = ConversationType.viber+userId;
        if (!conversations.containsKey(conversationId)) {
            conversations.put(conversationId, new ConversationState(userId, new ViberBotHandler()));
        }
        handleIncomingMessage(conversationId, input);

    }

    public void recieveTelegramMessage(String userId, Object input) {
        String conversationId = ConversationType.telegram+userId;
        if (!conversations.containsKey(conversationId)) {
            conversations.put(conversationId, new ConversationState(userId, new TelegramBotHandler()));
        }
        handleIncomingMessage(conversationId, input);
    }

    public void handleIncomingMessage(String conversationId, Object input) {
        ConversationState conversation = conversations.get(conversationId);
        conversation.accept(input);
    }
}
