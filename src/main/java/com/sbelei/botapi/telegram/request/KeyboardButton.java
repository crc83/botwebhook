package com.sbelei.botapi.telegram.request;

import java.util.Optional;

/**
 * Shortened (not full) KeyboardButton (part of ReplyKeyboardMarkup)
 * Taken from here: https://core.telegram.org/bots/api#keyboardbutton
 */
public class KeyboardButton {
    public String text; // 	String 	Text of the button. If none of the optional fields are used, it will be sent as a message when the button is pressed
    public Boolean request_contact; // 	Boolean 	Optional. If True, the user's phone number will be sent as a contact when the button is pressed. Available in private chats only
    public Boolean request_location;// 	Boolean 	Optional. If True, the user's current location will be sent when the button is pressed. Available in private chats only
}
