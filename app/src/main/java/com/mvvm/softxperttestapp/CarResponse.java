package com.mvvm.softxperttestapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarResponse {
    @SerializedName("data")
    private List<Car> data;

    public List<Car> getData() {
        return data;
    }

    public void setData(List<Car> data) {
        this.data = data;

    }

}
