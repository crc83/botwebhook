package com.sbelei.botapi.common;

public interface BotHandlerInterface {

    void sendMessage(String message);

    String getBotType();

    String getPhoneNumber(Object input);

    String getActionName(Object input);

    void send4SchedulesKeyboard();

    void sendShareContactKeyboard();
}
