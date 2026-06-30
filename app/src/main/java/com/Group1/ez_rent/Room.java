package com.Group1.ez_rent;

public class Room {
    private int imageResId;
    private String price, title, info, time;

    public Room(int imageResId, String price, String title, String info, String time) {
        this.imageResId = imageResId;
        this.price = price;
        this.title = title;
        this.info = info;
        this.time = time;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getTime() {
        return time;
    }
}