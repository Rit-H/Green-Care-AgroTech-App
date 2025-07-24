package com.exampleee.agroapk.models;

public class Category {


    public static Category category;
    String id,title,cover;
    public Category(String id, String title, String cover) {
        this.id = id;
        this.title = title;
        this.cover = cover;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }
}
