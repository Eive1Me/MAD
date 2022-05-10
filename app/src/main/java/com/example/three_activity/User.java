package com.example.three_activity;

public class User {
    private long id;
    private String name;
    private int picResource;
    private String city;
    private int score;

    public User(long id, String name, String city, int pic, int score){
        this.id = id;
        this.name = name;
        this.city = city;
        this.picResource = pic;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
