package com.exampleee.agroapk.models;

public class Order {

    public static Order order;

    String id;
    String product_id;
    String title;
    String cover;
    String date;
    String delivery_status;
    String price;


    public Order(String id, String title, String cover, String date, String delivery_status, String price, String product_id) {
        this.id = id;
        this.product_id = product_id;
        this.title = title;
        this.cover = cover;
        this.date = date;
        this.delivery_status = delivery_status;
        this.price = price;

    }

    public String getId() {
        return id;
    }
    public String getProduct_id() {
        return product_id;
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

    public String getDelivery_status() {
        return delivery_status;
    }

    public String getPrice() {
        return price;
    }


}
