package com.sbelei.botapi.telegram.responce.common;

/**
 * Full user model taken from:
 * https://core.telegram.org/bots/api#user
 */
public class User {
    public String id;                                  // 	Integer 	Unique identifier for this user or bot. This number may have more than 32 significant bits and some programming languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a 64-bit integer or double-precision float type are safe for storing this identifier.
    public boolean is_bot;                          // 	Boolean 	True, if this user is a bot
    public String first_name;                       // 	String 	User's or bot's first name
    public String last_name;                        // 	String 	Optional. User's or bot's last name
    public String username;                         // 	String 	Optional. User's or bot's username
    public String language_code;                    // 	String 	Optional. IETF language tag of the user's language
    public boolean can_join_groups;                 //  Boolean 	Optional. True, if the bot can be invited to groups. Returned only in getMe.
    public boolean can_read_all_group_messages;     // 	Boolean 	Optional. True, if privacy mode is disabled for the bot. Returned only in getMe.
    public boolean supports_inline_queries;         // 	Boolean 	Optional. True, if the bot supports inline queries. Returned only in getMe.
}
