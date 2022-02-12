package com.sbelei.botapi.telegram.responce.common;

import com.sbelei.botapi.telegram.responce.common.Message;

/**
 * Shortened (not full) Chat object taken from:
 * https://core.telegram.org/bots/api#chat
 */
public class Chat {
    public int id;                          // 	Integer 	Unique identifier for this chat. This number may have more than 32 significant bits and some programming languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a signed 64-bit integer or double-precision float type are safe for storing this identifier.
    public String type;                     // 	String 	Type of chat, can be either “private”, “group”, “supergroup” or “channel”
    public String title;                    // 	String 	Optional. Title, for supergroups, channels and group chats
    public String username;                 // 	String 	Optional. Username, for private chats, supergroups and channels if available
    public String first_name;               // 	String 	Optional. First name of the other party in a private chat
    public String last_name;                // 	String 	Optional. Last name of the other party in a private chat
    public String bio;                      // 	String 	Optional. Bio of the other party in a private chat. Returned only in getChat.
    public boolean has_private_forwards;    // 	True 	Optional. True, if privacy settings of the other party in the private chat allows to use tg://user?id=<user_id> links only in chats with the user. Returned only in getChat.
    public String description;              // 	String 	Optional. Description, for groups, supergroups and channel chats. Returned only in getChat.
    public String invite_link;              // 	String 	Optional. Primary invite link, for groups, supergroups and channel chats. Returned only in getChat.
    public Message pinned_message;          // 	Message 	Optional. The most recent pinned message (by sending date). Returned only in getChat.
}
