package com.example.myshoppingapp.models;

import java.io.Serializable;

public class NavCategoryModel implements Serializable{
    String name;
    String image_url;
    String description;
    String discount;
    String type;

    public NavCategoryModel() {
    }

    public NavCategoryModel(String name, String image_url, String description, String discount,String type) {
        this.name = name;
        this.image_url = image_url;
        this.description = description;
        this.discount = discount;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
