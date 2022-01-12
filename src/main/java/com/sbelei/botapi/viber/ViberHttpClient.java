package com.sbelei.botapi.viber;

import com.sbelei.botapi.common.LoggingInterceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ViberHttpClient {

    @Value("${viber.bot_token}")
    private String viberBotTokenId;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

    public String post(String url, String json) throws IOException {
        okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Viber-Auth-Token",viberBotTokenId)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if ((response.body() != null)) {
                return response.body().string();
            } else {
                return "";
            }
        }
    }

    public String send_message(String json) throws IOException {
        return post("https://chatapi.viber.com/pa/send_message", json);
    }
}
