package com.example.myshoppingapp.models;

public class HomeCategory {
    String name;
    String type;
    String image_url;



    public HomeCategory() {
    }

    public HomeCategory(String name, String type, String image_url) {
        this.name = name;
        this.type = type;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
