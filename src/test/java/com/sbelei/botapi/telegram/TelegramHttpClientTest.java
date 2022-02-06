package com.sbelei.botapi.telegram;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWireMock
class TelegramHttpClientTest {


    @Autowired
    private TelegramHttpClient client;

    @Test
    void testPost() {
        String actual = client.post("getMe", "");
        assertEquals("schedulify", JsonPath.read(actual, "$.result.first_name"));
    }

    @Test
    void testDeleteWebhook() {
        boolean actual = client.deleteWebhook();
        assertTrue(actual);
    }
}