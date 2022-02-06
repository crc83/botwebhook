package com.sbelei.botapi.telegram.responce;

public class WebhookInfo {
    public String url;// 	String 	Webhook URL, may be empty if webhook is not set up
    public boolean has_custom_certificate;// 	Boolean 	True, if a custom certificate was provided for webhook certificate checks
    public int pending_update_count;// 	Integer 	Number of updates awaiting delivery
    public String ip_address;// 	String 	Optional. Currently used webhook IP address
    public int last_error_date;// 	Integer 	Optional. Unix time for the most recent error that happened when trying to deliver an update via webhook
    public String last_error_message; // 	String 	Optional. Error message in human-readable format for the most recent error that happened when trying to deliver an update via webhook
    public int max_connections;// 	Integer 	Optional. Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery
    public String[] allowed_updates;// 	Array of String 	Optional. A list of update types the bot is subscribed to. Defaults to all update types except chat_member
}
