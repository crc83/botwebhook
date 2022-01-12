package com.sbelei.botapi.viber.request.incomingmessage;

public class IncomingEvent {

    public String event;
    public String timestamp;
    public String chat_hostname;
    public String message_token;
    public Sender sender;
    public Message message;
    public boolean silent;

    @Override
    public String toString() {
        return "IncomingMessageRequest{" +
                "event='" + event + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", chat_hostname='" + chat_hostname + '\'' +
                ", message_token='" + message_token + '\'' +
                ", sender=" + sender!=null?sender.toString():"null" +
                ", message=" + message!=null?message.toString():"null" +
                ", silent=" + silent +
                '}';
    }
}
