package com.sbelei.botapi.common;

public class StubAppHelper implements AppHelperInterface{
    @Override
    public boolean isUserRegistered(String userId, String botType) {
        return false;
    }

    @Override
    public boolean isPhoneNumberValid(Object input) {
        return false;
    }

    @Override
    public boolean phoneNumberIsKnown(String phoneNumber) {
        return false;
    }

    @Override
    public String getBotRegisteredForPhone(String phoneNumber, String botType) {
        return null;
    }

    @Override
    public void saveContact(String phoneNumber, String userId, String botType) {

    }

    @Override
    public void showSchedule(String actionName) {

    }
}
