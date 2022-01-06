package com.sbelei.viberbot.request.incomingmessage;

/**
 * Belongs to IncomingMessageRequest, hide it there
 */
public class Message {
    public String text;
    public String type;
    public Contact contact;

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
