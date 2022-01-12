package com.sbelei.botapi.viber.request.incomingmessage;

/**
 * Belongs to IncomingMessageRequest, hide it there
 */
public class Sender {
    public String id;
    public String name;
    public String avatar;
    public String language;
    public String country;
    public int api_version;

    @Override
    public String toString() {
        return "Sender{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", api_version=" + api_version +
                '}';
    }
}
