package com.sbelei.botapi.common;

import com.sbelei.BotwebhooksApplication;
import com.sbelei.botapi.telegram.TelegramBotHandler;
import com.sbelei.botapi.viber.ViberBotHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest(classes = BotwebhooksApplication.class)
class BotControllerTest {

    @Autowired
    MockMvc botMockMvc;



    @DisplayName("Telegram incoming: simple 'Hello'")
    @Test
    void testPostFromTelegram() throws Exception {
        botMockMvc.perform(post("/testurl/telegram", "{\"update_id\":88062603,\"message\":{\"message_id\":40,\"from\":{\"id\":504734059,\"is_bot\":false,\"first_name\":\"Сергій\",\"last_name\":\"Белей\",\"username\":\"crc83\",\"language_code\":\"uk\"},\"chat\":{\"id\":504734059,\"first_name\":\"Сергій\",\"last_name\":\"Белей\",\"username\":\"crc83\",\"type\":\"private\"},\"date\":1643467139,\"text\":\"Hello\"}}"));
    }
}