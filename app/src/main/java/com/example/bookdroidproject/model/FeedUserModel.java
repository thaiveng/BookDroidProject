package com.example.bookdroidproject.model;

public class FeedUserModel {


    private String username;
    private String follower;
    private String recommendation;
    private int img_user_feed;



    public FeedUserModel(String username, String follower, String recommendation, int img_user_feed) {
        this.username = username;
        this.follower = follower;
        this.recommendation = recommendation;
        this.img_user_feed = img_user_feed;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public int getImg_user_feed() {
        return img_user_feed;
    }

    public void setImg_user_feed(int img_user_feed) {
        this.img_user_feed = img_user_feed;
    }
}
