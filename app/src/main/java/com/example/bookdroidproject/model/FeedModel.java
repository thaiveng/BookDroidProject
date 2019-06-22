package com.example.bookdroidproject.model;

public class FeedModel  {
    String title,description;
    int imgFeed;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgFeed() {
        return imgFeed;
    }

    public void setImgFeed(int imgFeed) {
        this.imgFeed = imgFeed;
    }

    public FeedModel(String title, String description, int imgFeed) {
        this.title = title;
        this.description = description;
        this.imgFeed = imgFeed;
    }
}
