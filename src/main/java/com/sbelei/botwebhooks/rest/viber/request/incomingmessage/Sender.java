package com.sbelei.botwebhooks.rest.viber.request.incomingmessage;

/**
 * Belongs to IncomingMessageRequest, hide it there
 */
public class Sender {
    private String id;
    private String name;
    private String avatar;
    private String language;
    private String country;
    private int api_version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getApi_version() {
        return api_version;
    }

    public void setApi_version(int api_version) {
        this.api_version = api_version;
    }

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
