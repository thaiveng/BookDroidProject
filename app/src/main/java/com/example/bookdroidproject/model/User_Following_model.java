package com.example.bookdroidproject.model;

public class User_Following_model {

    private int imgID;
    private String username;
    private String text_button;


    public User_Following_model(int imgID, String username, String text_button) {
        this.imgID = imgID;
        this.username = username;
        this.text_button = text_button;
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

    public String getText_button() {
        return text_button;
    }

    public void setText_button(String text_button) {
        this.text_button = text_button;
    }
}
