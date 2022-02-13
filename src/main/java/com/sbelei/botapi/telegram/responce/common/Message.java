package com.sbelei.botapi.telegram.responce.common;

/**
 * Shortened (not full) Message model from here:
 * https://core.telegram.org/bots/api#message
 */
public class Message {

    public String message_id;               // 	* Unique message identifier inside this chat
    public User from;                       // 	Optional. Sender of the message; empty for messages sent to channel's. For backward compatibility, the field contains a fake sender user in non-channel chats, if the message was sent on behalf of a chat.
    public Chat sender_chat;                // 	Optional. Sender of the message, sent on behalf of a chat. For example, the channel itself for channel posts, the supergroup itself for messages from anonymous group administrators, the linked channel for messages automatically forwarded to the discussion group. For backward compatibility, the field from contains a fake sender user in non-channel chats, if the message was sent on behalf of a chat.
    public int date;                        // 	* Date the message was sent in Unix time
    public Chat chat;                       // 	* Conversation the message belongs to
    public User forward_from;               // 	Optional. For forwarded messages, sender of the original message
    public Chat forward_from_chat;          // 	Optional. For messages forwarded from channels or from anonymous administrators, information about the original sender chat
    public int forward_from_message_id;     // 	Optional. For messages forwarded from channels, identifier of the original message in the channel
    public String forward_signature;        // 	Optional. For forwarded messages that were originally sent in channels or by an anonymous chat administrator, signature of the message sender if present
    public String forward_sender_name;      // 	Optional. Sender's name for messages forwarded from users who disallow adding a link to their account in forwarded messages
    public int forward_date;                // 	Optional. For forwarded messages, date the original message was sent in Unix time
    public boolean is_automatic_forward;    // 	Optional. True, if the message is a channel post that was automatically forwarded to the connected discussion group
    public Message reply_to_message;        // 	Optional. For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
    public User via_bot;                    // 	Optional. Bot through which the message was sent
    public int edit_date;                   // 	Optional. Date the message was last edited in Unix time
    public boolean has_protected_content;   // 	Optional. True, if the message can't be forwarded
    public String media_group_id;           // 	Optional. The unique identifier of a media message group this message belongs to
    public String author_signature;         // 	Optional. Signature of the post author for messages in channels, or the custom title of an anonymous group administrator
    public String text;                     // 	Optional. For text messages, the actual UTF-8 text of the message, 0-4096 characters
    public MessageEntity[] entities;        // 	Optional. For text messages, special entities like usernames, URLs, bot commands, etc. that appear in the text
    public Contact contact;                 // 	Optional. Message is a shared contact, information about the contact
}
