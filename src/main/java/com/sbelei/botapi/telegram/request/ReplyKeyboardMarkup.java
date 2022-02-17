package com.sbelei.botapi.telegram.request;

import java.util.Optional;

/**
 * Shortened (not full) version of ReplyKeyboardMarkup
 * Taken from here : https://core.telegram.org/bots/api#replykeyboardmarkup
 */
public class ReplyKeyboardMarkup extends BaseKeyboardMarkup {
    public KeyboardButton[][] keyboard;         // 	Array of KeyboardButton 	Array of button rows, each represented by an Array of KeyboardButton objects
    public Boolean resize_keyboard;             // 	Boolean 	Optional. Requests clients to resize the keyboard vertically for optimal fit (e.g., make the keyboard smaller if there are just two rows of buttons). Defaults to false, in which case the custom keyboard is always of the same height as the app's standard keyboard.
    public Boolean one_time_keyboard;           // 	Boolean 	Optional. Requests clients to hide the keyboard as soon as it's been used. The keyboard will still be available, but clients will automatically display the usual letter-keyboard in the chat – the user can press a special button in the input field to see the custom keyboard again. Defaults to false.
    public String input_field_placeholder;      // 	String 	Optional. The placeholder to be shown in the input field when the keyboard is active; 1-64 characters
    public Boolean selective;                   // 	Boolean 	Optional. Use this parameter if you want to show the keyboard to specific users only. Targets: 1) users that are @mentioned in the text of the Message object; 2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
}
