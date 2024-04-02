package com.example.myshoppingapp.models;

public class PopularModels {
    String name;
    String image_url;
    String desc;
    String rating;
    String discount;
    String type;


    public PopularModels() {
    }

    public PopularModels(String name, String image_url, String desc, String rating,String discount,String type) {
        this.name = name;
        this.image_url = image_url;
        this.desc = desc;
        this.type=type;


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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDiscount(){
        return discount;
    }

    public void setDiscount(String discount){
        this.discount=discount;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }
}
