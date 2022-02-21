package com.sbelei.botapi.common;

import com.sbelei.BotwebhooksApplication;
import com.sbelei.botapi.telegram.TelegramHttpClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class BotControllerTest {

    @Autowired
    MockMvc botMockMvc;

    @MockBean
    private TelegramHttpClient client;

    @DisplayName("Telegram incoming: simple 'Hello'")
    @Test
    void testPostFromTelegram() throws Exception {
        botMockMvc.perform(post("/testurl/telegram")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"update_id\":88062603,\"message\":{\"message_id\":40,\"from\":{\"id\":504734059,\"is_bot\":false,\"first_name\":\"Сергій\",\"last_name\":\"Белей\",\"username\":\"crc83\",\"language_code\":\"uk\"},\"chat\":{\"id\":504734059,\"first_name\":\"Сергій\",\"last_name\":\"Белей\",\"username\":\"crc83\",\"type\":\"private\"},\"date\":1643467139,\"text\":\"Hello\"}}"))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));

        verify(client).sendMessage(eq("504734059"),
                eq("Nice to meet you first time here"));
        verify(client).sendShareContactKeyboard( eq("504734059"),
                eq(ConversationState.ASK_PHONE_NUMBER_MESSAGE), eq(ConversationState.SHARE_CONTACT_BUTTON_CAPTION));
        verify(client).getBotType();

        verifyNoMoreInteractions(client);
    }
}