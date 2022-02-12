package com.sbelei.botapi.telegram.request;

/**
 * Shortened (not full) send message request is taken from here
 * https://core.telegram.org/bots/api#sendmessage
 */
public class SendMessageRequest {

    public String chat_id;      // 	Integer or String 	Yes 	Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    public String text;         // 	String 	Yes 	Text of the message to be sent, 1-4096 characters after entities parsing
//    public String parse_mode;   // 	String 	Optional 	Mode for parsing entities in the message text. See formatting options for more details.
//    public MessageEntity[] entities;     // 	Array of MessageEntity 	Optional 	A JSON-serialized list of special entities that appear in message text, which can be specified instead of parse_mode
//    public boolean disable_web_page_preview;     // 	Boolean 	Optional 	Disables link previews for links in this message
//    public boolean disable_notification;         // 	Boolean 	Optional 	Sends the message silently. Users will receive a notification with no sound.
//    public boolean protect_content;              // 	Boolean 	Optional 	Protects the contents of the sent message from forwarding and saving
//    public int reply_to_message_id;                 // 	Integer 	Optional 	If the message is a reply, ID of the original message
//    public boolean allow_sending_without_reply;     // 	Boolean 	Optional 	Pass True, if the message should be sent even if the specified replied-to message is not found
//    public String reply_markup;                 // 	InlineKeyboardMarkup or ReplyKeyboardMarkup or ReplyKeyboardRemove or ForceReply 	Optional 	Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to remove reply keyboard or to force a reply from the user.
}
