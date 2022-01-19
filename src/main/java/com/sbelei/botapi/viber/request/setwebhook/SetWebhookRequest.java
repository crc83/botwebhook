package com.sbelei.botapi.viber.request.setwebhook;

public class SetWebhookRequest {

    public String url;
    public EventType[] event_types; //array enums,
    public boolean send_name;
    public boolean send_photo;
}
