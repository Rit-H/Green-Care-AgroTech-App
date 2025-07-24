package com.exampleee.agroapk.models;

public class Notification {

    public static Notification notification;


    String title,cover,date,type,value;

    boolean read;


    public Notification(String title, String cover, String date, String type, String value, boolean read) {
        this.title = title;
        this.cover = cover;
        this.date = date;
        this.type = type;
        this.value = value;
        this.read = read;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }


}
