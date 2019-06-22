package com.example.bookdroidproject.model;

public class User_Following_model {

    private int imgID;
    private String username;


    public User_Following_model(int imgID, String username) {
        this.imgID = imgID;
        this.username = username;
    }


    public User_Following_model(){


    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
