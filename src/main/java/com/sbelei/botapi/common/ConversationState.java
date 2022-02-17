package com.sbelei.botapi.common;

public class ConversationState {

    private static final String INTRO_MESSAGE = "Nice to meet you first time here";
    private static final String PHONE_NUMBER_IS_INVALID = "Phone number is invalid\nPlease try again";
    private static final String NO_USER_WITH_THIS_PHONE_NUMBER = "There is no user with this phone number in system\nI can't register you";
    private static final String THERE_IS_MESSENGER_REGISTERED_FOR_THIS = "The user for chatbot is already registered for his number";
    private static final String ASK_PHONE_NUMBER_MESSAGE = "To get most of the platform, please sent us your phone number you use during registration in a next message\nOr press 'Share contact'";
    //"Надішліть свій номер в форматі 38ХХХХХХХХХХ або натисніть 'Надіслати номер' "
    private static final String WHAT_TO_SHOW_YOU = "Please choose what to show you";
    private static final String SHARE_CONTACT_BUTTON_CAPTION = "Share phone number";//"Надіслати мій номер"

    enum ConvStage {
        USER_HELLO,
        USER_RESPONDED_TO_ASK_CONTACT,
        USER_RESPONDED_FOR_ACTION,
    }

    private String userId;
    private ConvStage state = ConvStage.USER_HELLO;
    private BotHandlerInterface botHandler;
    private AppHelperInterface appHelper; // <- this is door to our application world (maybe put it to botHandler???)


    public ConversationState(String userId, BotHandlerInterface botHandler) {
        this.userId = userId;
        this.botHandler = botHandler;
    }

    public void accept(Object input) throws Exception {
        switch (state) {
            case USER_HELLO:
                botHandler.sendMessage(userId, INTRO_MESSAGE);
                if (appHelper.isUserRegistered(userId, botHandler.getBotType())) {
                    botAsksForAction();
                } else {
                    botAsksContact();
                }
                break;

            case USER_RESPONDED_TO_ASK_CONTACT:
                if (!appHelper.isPhoneNumberValid(input)) {
                    botHandler.sendMessage(userId, PHONE_NUMBER_IS_INVALID);
                    botAsksContact();
                }
                String phoneNumber = botHandler.getPhoneNumber(input);
                if (!appHelper.phoneNumberIsKnown(phoneNumber)) {
                    botHandler.sendMessage(userId, NO_USER_WITH_THIS_PHONE_NUMBER);
                    botAsksContact();
                }
                if (appHelper.getBotRegisteredForPhone(phoneNumber, botHandler.getBotType()) != null) {
                    botHandler.sendMessage(userId, THERE_IS_MESSENGER_REGISTERED_FOR_THIS);
                    botAsksContact();
                }
                appHelper.saveContact(phoneNumber, userId, botHandler.getBotType());
                break;

            case USER_RESPONDED_FOR_ACTION:
                appHelper.showSchedule(botHandler.getActionName(input));
                botAsksForAction();
                break;
        }
    }

    private void botAsksForAction() throws Exception {
        botHandler.sendMessage(userId, WHAT_TO_SHOW_YOU);
        botHandler.send4SchedulesKeyboard();
        state = ConvStage.USER_RESPONDED_FOR_ACTION;
    }

    private void botAsksContact() throws Exception {
        botHandler.sendShareContactKeyboard(userId, ASK_PHONE_NUMBER_MESSAGE, SHARE_CONTACT_BUTTON_CAPTION);
        state = ConvStage.USER_RESPONDED_TO_ASK_CONTACT;
    }

}
