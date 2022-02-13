package com.sbelei.botapi.telegram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import com.sbelei.botapi.telegram.responce.getupdate.Update;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.*;

//@ActiveProfiles("proxy")
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
    void testSetWebhook() {
        assertTrue(client.setWebhook("https://bots.schedulify.com.ua/test"));
    }

    @Test
    void testDeleteWebhook() {
        assertTrue( client.deleteWebhook());
    }

    @Test
    void testGetUpdate() throws JsonProcessingException {
        Update[] actual = client.getUpdates();
        assertAll(() -> {
            assertNotNull(actual);
            assertNotEquals(0, actual.length);
            assertEquals(3, actual.length);
            assertEquals("crc83", actual[0].message.from.username);
        });

    }

    @Test
    void testSendMessage() throws Exception{
        //in case of error, method throws exceptions
        client.sendMessage("504734059", "Hello world");
    }
}