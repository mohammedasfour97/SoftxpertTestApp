package com.mvvm.softxperttestapp;

import com.google.gson.annotations.SerializedName;

public class Car {

    @SerializedName("id")
    private String Name;

    @SerializedName("brand")
    private String brand;

    @SerializedName("constractionYear")
    private String constraction_year;

    @SerializedName("isUsed")
    private String is_used;

    @SerializedName("imageUrl")
    private String img_url;

    public Car(String name, String brand, String constraction_year, String is_used, String img_url) {
        Name = name;
        this.brand = brand;
        this.constraction_year = constraction_year;
        this.is_used = is_used;
        this.img_url = img_url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getConstraction_year() {
        return constraction_year;
    }

    public void setConstraction_year(String constraction_year) {
        this.constraction_year = constraction_year;
    }

    public String getIs_used() {
        return is_used;
    }

    public void setIs_used(String is_used) {
        this.is_used = is_used;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
