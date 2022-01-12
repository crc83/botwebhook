package com.sbelei.botapi.viber.request.buttons;

public class KeyboardRequest {
    public String receiver;
    public String min_api_version;
    public String type;
    public String text;
    public Keyboard keyboard;

    public KeyboardRequest() {
        min_api_version = "7";
        type = "text";
    }
}
