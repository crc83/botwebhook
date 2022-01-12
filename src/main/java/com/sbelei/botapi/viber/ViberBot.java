package com.sbelei.botapi.viber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbelei.botapi.viber.request.setwebhook.EventType;
import com.sbelei.botapi.viber.request.setwebhook.SetWebhookRequest;
import org.springframework.stereotype.Component;

@Component
public class ViberBot {


    public String setWebhookUrl(String url) throws JsonProcessingException {
        SetWebhookRequest request =  new SetWebhookRequest();
        request.setUrl(url);
        request.setEvent_types( new EventType[] {
                EventType.seen,
                EventType.conversation_started,
                EventType.delivered,
                EventType.failed,
                EventType.subscribed,
                EventType.unsubscribed
        });
        request.setSend_name(true);
        request.setSend_photo(true);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(request);
    }

    public String sendTextMessage(String userId, String message) {
        return "{\n" +
                "   \"receiver\":\""+userId+"==\",\n" +
                "   \"min_api_version\":1,\n" +
                "   \"sender\":{\n" +
                "      \"name\":\"schedulify.com.ua\",\n" +
                "      \"avatar\":\"http://avatar.example.com\"\n" +
                "   },\n" +
                "   \"tracking_data\":\"tracking data\",\n" +
                "   \"type\":\"text\",\n" +
                "   \"text\":\""+message+"\"\n" +
                "}";
    }
}
