package com.sbelei.botapi.viber;

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
public class ViberHttpClient {

    Logger LOG = LoggerFactory.getLogger(ViberHttpClient.class);

    @Value("${botapi.viber.bot_token}")
    private String viberBotTokenId;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

    public String post(String url, String json) {
        String result = "error";

        okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Viber-Auth-Token",viberBotTokenId)
                .post(body)
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
}
