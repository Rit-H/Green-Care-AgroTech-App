package com.exampleee.agroapk.models;

public class Slider {


    String cover,type,value;


    public Slider(String cover, String type, String value) {
        this.type = type;
        this.cover = cover;
        this.value = value;

    }
    public String getCover() {
        return cover;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
