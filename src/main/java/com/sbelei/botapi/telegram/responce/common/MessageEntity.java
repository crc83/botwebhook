package com.sbelei.botapi.telegram.responce.common;

import com.sbelei.botapi.telegram.responce.common.User;

public class MessageEntity {
    public String type;     // 	String 	Type of the entity. Currently, can be “mention” (@username), “hashtag” (#hashtag), “cashtag” ($USD), “bot_command” (/start@jobs_bot), “url” (https://telegram.org), “email” (do-not-reply@telegram.org), “phone_number” (+1-212-555-0123), “bold” (bold text), “italic” (italic text), “underline” (underlined text), “strikethrough” (strikethrough text), “spoiler” (spoiler message), “code” (monowidth string), “pre” (monowidth block), “text_link” (for clickable text URLs), “text_mention” (for users without usernames)
    public int offset;      // 	Integer 	Offset in UTF-16 code units to the start of the entity
    public int length;      // 	Integer 	Length of the entity in UTF-16 code units
    public String url;      // 	String 	Optional. For “text_link” only, url that will be opened after user taps on the text
    public User user;       // 	User 	Optional. For “text_mention” only, the mentioned user
    public String language; // 	String 	Optional. For “pre” only, the programming language of the entity text
}
