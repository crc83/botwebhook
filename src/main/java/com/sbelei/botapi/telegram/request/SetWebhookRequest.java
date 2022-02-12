package com.sbelei.botapi.telegram.request;

/**
 * Shortened (not full) set webhook request params taken from here:
 * https://core.telegram.org/bots/api#setwebhook
 */
public class SetWebhookRequest {
    public String url;                  // 	HTTPS url to send updates to. Use an empty string to remove webhook integration
//    public String ip_address;           // 	Optional 	The fixed IP address which will be used to send webhook requests instead of the IP address resolved through DNS
//    public int max_connections;         // 	Optional 	Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery, 1-100. Defaults to 40. Use lower values to limit the load on your bot's server, and higher values to increase your bot's throughput.
//    public String[] allowed_updates;    // 	Optional 	A JSON-serialized list of the update types you want your bot to receive. For example, specify [“message”, “edited_channel_post”, “callback_query”] to only receive updates of these types. See Update for a complete list of available update types. Specify an empty list to receive all update types except chat_member (default). If not specified, the previous setting will be used.
    public boolean drop_pending_updates;// 	Optional 	Pass True to drop all pending updates
}
