package com.example.myapplication;

public class ItemData {
    private String title;
    private String address;
    private String imageId;

    public ItemData(String title, String imageId, String address) {
        this.title = title;
        this.imageId = imageId;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public String getImageId() {
        return imageId;
    }

    public String getAddress() {
        return address;
    }
}
