package com.sbelei.botapi.telegram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import com.sbelei.botapi.common.BotHandlerInterface;
import com.sbelei.botapi.common.CommonObjectMapper;
import com.sbelei.botapi.common.LoggingInterceptor;
import com.sbelei.botapi.telegram.request.SendMessageRequest;
import com.sbelei.botapi.telegram.responce.SendMessageResponse;
import com.sbelei.botapi.telegram.responce.getupdate.GetUpdateResponse;
import com.sbelei.botapi.telegram.responce.getupdate.Update;
import com.sbelei.botapi.telegram.request.SetWebhookRequest;
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
//    @Value("${botapi.telegrambot.name}")
//    private String botName;
    @Value("${botapi.telegrambot.token}")
    private String token;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

    private CommonObjectMapper mapper = new CommonObjectMapper();

    public boolean deleteWebhook() {
        return JsonPath.read( post("deleteWebhook", ""), "$.result");
    }

    public boolean setWebhook(String url) {
        SetWebhookRequest setWebhookRequest = new SetWebhookRequest();
        setWebhookRequest.url = url;
        setWebhookRequest.drop_pending_updates = false;
        return JsonPath.read(  post("setWebhook", mapper.toJson(setWebhookRequest)), "$.result");
    }

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
            LOG.error("Error sending request to telegram server", e);
        }
        return result;
    }

    @Override
    public void sendMessage(String userId, String message) throws Exception {
        SendMessageRequest sendMessageRequest =  new SendMessageRequest();
        sendMessageRequest.chat_id = userId;//
        sendMessageRequest.text = message;
        SendMessageResponse response = mapper.readValue( post("sendMessage", mapper.toJson(sendMessageRequest)), SendMessageResponse.class);
        if (!response.ok) {
            LOG.error("Error sending telegram message");
            //TODO SB : Retry logic?
        }
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

    /**
     * This methods gets updates from server
     * It receives same response as webhook endpoint
     * Since we can't actively develop using webhook,
     * we use this method for development and tests
     *
     *
     * @return
     */
    public Update[] getUpdates() throws JsonProcessingException {
        String response = post("getUpdates", "");
        GetUpdateResponse getUpdateResponse = mapper.readValue(response, GetUpdateResponse.class);
        if (getUpdateResponse.ok) {
            return getUpdateResponse.result;
        } else {
            LOG.warn("Can't get updates due to some error");
            return new Update[]{};
        }
    }
}
