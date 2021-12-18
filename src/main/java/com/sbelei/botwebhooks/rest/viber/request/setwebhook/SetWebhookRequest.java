package com.sbelei.botwebhooks.rest.viber.request.setwebhook;

public class SetWebhookRequest {

    private String url;
    private EventType[] event_types; //array enums,
    private boolean send_name;
    private boolean send_photo;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public EventType[] getEvent_types() {
        return event_types;
    }

    public void setEvent_types(EventType[] event_types) {
        this.event_types = event_types;
    }

    public boolean isSend_name() {
        return send_name;
    }

    public void setSend_name(boolean send_name) {
        this.send_name = send_name;
    }

    public boolean isSend_photo() {
        return send_photo;
    }

    public void setSend_photo(boolean send_photo) {
        this.send_photo = send_photo;
    }
}
