package com.exampleee.agroapk.models;

public class CartsItem {

    String id,product_id,title,cover;
    int quantity , price,sprice;

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

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getSprice() {
        return sprice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartsItem(String id, String product_id, String title, String cover, int quantity, int price, int sprice) {
        this.id = id;
        this.product_id = product_id;
        this.title = title;
        this.cover = cover;
        this.quantity = quantity;
        this.price = price;
        this.sprice = sprice;
    }
}
