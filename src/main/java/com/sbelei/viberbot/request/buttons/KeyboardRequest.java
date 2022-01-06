package com.sbelei.viberbot.request.buttons;

public class KeyboardRequest {
    private String receiver;
    private String min_api_version;
    private String type;
    private String text;
    private Keyboard keyboard;

    public KeyboardRequest() {
        min_api_version = "7";
        type = "text";
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMin_api_version() {
        return min_api_version;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }
}
