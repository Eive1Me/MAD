package com.example.three_activity;

public class User {

    private String name;
    private int picResource;

    public User(String name, int pic){
        this.name = name;
        this.picResource = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicResource() {
        return picResource;
    }

    public void setPicResource(int picResource) {
        this.picResource = picResource;
    }
}
