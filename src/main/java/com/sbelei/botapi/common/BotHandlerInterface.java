package com.sbelei.botapi.common;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface BotHandlerInterface {

    void sendMessage(String userId, String message);

    String getBotType();

    String getPhoneNumber(Object input);

    String getActionName(Object input);

    void send4SchedulesKeyboard();

    void sendShareContactKeyboard(String userId);
}
