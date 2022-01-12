package com.sbelei;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbelei.botapi.common.LoggingInterceptor;
import com.sbelei.botapi.viber.ViberConversationManager;
import com.sbelei.botapi.viber.request.incomingmessage.IncomingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * This class connects to endpoint at
 * https://bots.schedulify.com.ua/viber/getevent
 * So deployed app works as a proxy (callbacks come to https://bots.schedulify.com.ua/viber/proxy)
 * for communication and I can prototype bot here
 */
@Component
public class ViberLocalHandler {

    @Autowired
    private ViberConversationManager viberConversationManager;

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

    @Scheduled(fixedRate = 500)
    public void poolMessagesAndResponse() {
        System.out.println("...,.,. scheduled task ...,.,.,.,,.");
        Request request = new Request.Builder()
                .url("https://bots.schedulify.com.ua/viber/getevent")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if ((response.code() == 200) && (response.body() != null )) {
                ObjectMapper objectMapper = new ObjectMapper();
                IncomingEvent event = objectMapper.readValue(response.body().byteStream(), IncomingEvent.class);

                viberConversationManager.respondToEvent(event);
                System.out.println(event.toString());
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
