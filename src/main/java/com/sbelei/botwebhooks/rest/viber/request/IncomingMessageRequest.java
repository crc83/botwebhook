package com.sbelei.botwebhooks.rest.viber.request;

public class IncomingMessageRequest {

    private String event;
    private String timestamp;
    private String chat_hostname;
    private String message_token;
    private Sender sender;
    private Message message;
    private boolean silent;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getChat_hostname() {
        return chat_hostname;
    }

    public void setChat_hostname(String chat_hostname) {
        this.chat_hostname = chat_hostname;
    }

    public String getMessage_token() {
        return message_token;
    }

    public void setMessage_token(String message_token) {
        this.message_token = message_token;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    @Override
    public String toString() {
        return "ViberIncomingMessageRequestDto{" +
                "event='" + event + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", chat_hostname='" + chat_hostname + '\'' +
                ", message_token='" + message_token + '\'' +
                ", sender=" + sender.toString() +
                ", message=" + message.toString() +
                ", silent=" + silent +
                '}';
    }
}
