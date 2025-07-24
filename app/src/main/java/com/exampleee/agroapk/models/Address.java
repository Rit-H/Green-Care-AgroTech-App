package com.exampleee.agroapk.models;

public class Address {

    public static Address addressObj;
    String id,user_id,name,mobile,email,address,landmark,pin_code,city,state;



    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getPin_code() {
        return pin_code;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public boolean isSelected() {
        return selected;
    }

    public Address(String id, String user_id, String name, String mobile, String email, String address, String landmark, String pin_code, String city, String state, boolean selected) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.landmark = landmark;
        this.pin_code = pin_code;
        this.city = city;
        this.state = state;
        this.selected = selected;
    }

    boolean selected;

}
