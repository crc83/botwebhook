package com.sbelei.botapi.telegram;

import com.jayway.jsonpath.JsonPath;
import com.sbelei.botapi.common.BotHandlerInterface;
import com.sbelei.botapi.common.LoggingInterceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TelegramHttpClient implements BotHandlerInterface {

    private final Logger LOG = LoggerFactory.getLogger(TelegramHttpClient.class);

    @Value("${botapi.telegrambot.server_url}")
    private String serverUrl;
    @Value("${botapi.telegrambot.name}")
    private String botName;
    @Value("${botapi.telegrambot.token}")
    private String token;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

    public boolean deleteWebhook() {
        return JsonPath.read( post("deleteWebhook", ""), "$.result");
    }

    //todo
    //set webhook

    public String post(String method, String json) {
        String result = "error";

        Request request = new Request.Builder()
                    .url(serverUrl + "/bot" + token + "/" + method)
                    .post(okhttp3.RequestBody.create(JSON, json))
                    .build();

        try (Response response = client.newCall(request).execute()) {
            if ((response.body() != null)) {
                result = response.body().string();
            } else {
                result = "";
            }
        } catch (IOException e) {
            //TODO SB: implement cirquit braker
            LOG.error("Error sending request to viber server", e);
            result = e.getMessage();
        }
        return result;
    }

    @Override
    public void sendMessage(String userId, String message) {

    }

    @Override
    public String getBotType() {
        return "telegram";
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

    }
}
