package com.exampleee.agroapk.models;

public class Products {
    public static String products_id;

    String id,title,cover,infovover, categoryId, description;
//    int price,sprice;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public String getInfovoverCover() {
        return infovover;
    }
    public String getDescription() { return description; }

//    public int getPrice() {
//        return price;
//    }

    public String getCategoryId() {
        return categoryId;
    }

//    public int getSprice() {
//        return sprice;
//    }

    public Products(String id, String title, String cover, int price, int sprice,String infovover, String categoryId, String description) {
        this.id = id;
        this.title = title;
        this.cover = cover;
//        this.price = price;
//        this.sprice = sprice;
        this.infovover=infovover;
        this.categoryId=categoryId;
        this.description=description;
    }
}
