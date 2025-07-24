package com.exampleee.agroapk.models;

public class Consult {


    public static Consult consult;

    String id;
    String name;


    public Consult() {
        // Default constructor required for Firebase
    }

    public Consult(String id, String name, String occupation, String profile, String chat, String call, String timing) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.profile = profile;
        this.chat = chat;
        this.call = call;
        this.timing = timing;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getProfile() {
        return profile;
    }

    public String getChat() {
        return chat;
    }

    public String getCall() {
        return call;
    }

    public String getTiming() {
        return timing;
    }

    String occupation;
    String profile;
    String chat;
    String call;
    String timing;

}
