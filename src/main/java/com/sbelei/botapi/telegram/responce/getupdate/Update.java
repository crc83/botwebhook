package com.sbelei.botapi.telegram.responce.getupdate;


import com.sbelei.botapi.telegram.responce.common.Message;

/**
 * Shortened (not full) Update model taken from:
 * https://core.telegram.org/bots/api#update
 */
public class Update {

    public int update_id;               // 	Integer 	The update's unique identifier. Update identifiers start from a certain positive number and increase sequentially. This ID becomes especially handy if you're using Webhooks, since it allows you to ignore repeated updates or to restore the correct update sequence, should they get out of order. If there are no new updates for at least a week, then identifier of the next update will be chosen randomly instead of sequentially.
    public Message message;             // 	Message 	Optional. New incoming message of any kind — text, photo, sticker, etc.
    public Message edited_message;      // 	Message 	Optional. New version of a message that is known to the bot and was edited
    public Message channel_post;        // 	Message 	Optional. New incoming channel post of any kind — text, photo, sticker, etc.
    public Message edited_channel_post; // 	Message 	Optional. New version of a channel post that is known to the bot and was edited
}
