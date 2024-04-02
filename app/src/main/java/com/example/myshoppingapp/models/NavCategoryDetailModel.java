package com.example.myshoppingapp.models;

public class NavCategoryDetailModel {
    String name;
    String image_url;
    int price;
    String type;

    public NavCategoryDetailModel() {
    }

    public NavCategoryDetailModel(String name, String image_url, int price, String type) {
        this.name = name;
        this.image_url = image_url;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
