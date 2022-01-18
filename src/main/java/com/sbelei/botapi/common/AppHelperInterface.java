package com.sbelei.botapi.common;

public interface AppHelperInterface {
    boolean isUserRegistered(String userId, String botType);

    boolean isPhoneNumberValid(Object input);

    boolean phoneNumberIsKnown(String phoneNumber);

    String getBotRegisteredForPhone(String phoneNumber, String botType);

    void saveContact(String phoneNumber, String userId, String botType);

    void showSchedule(String actionName);
}
