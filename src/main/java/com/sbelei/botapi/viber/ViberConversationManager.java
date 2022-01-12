package com.sbelei.botapi.viber;

import com.sbelei.botapi.viber.request.incomingmessage.IncomingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ViberConversationManager {

    Map<String, ViberConversationState> conversations = new HashMap<>();

    @Autowired
    private ViberHttpClient client;

    public void respondToEvent(IncomingEvent event) throws IOException {
        if (conversations.containsKey(event.getSender().getId())) {
            ViberConversationState conversation = conversations.get(event.getSender().getId());
            conversation.respondToEvent(client, event);
        } else {
            ViberConversationState newConversation = new ViberConversationState();
            conversations.put(event.getSender().getId(), newConversation);
            newConversation.startConversation(client, event);
        }
    }
}
