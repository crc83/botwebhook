package com.sbelei.viberbot.request.incomingmessage;

/**
 * Belongs to IncomingMessageRequest, hide it there
 */
public class Message {
    private String text;
    private String type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
