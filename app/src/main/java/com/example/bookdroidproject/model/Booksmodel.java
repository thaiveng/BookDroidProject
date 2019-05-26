package com.example.bookdroidproject.model;

public class BooksModel {

    private String title_book;
    private int imgID;

    public BooksModel(String title_book, int imgID) {
        this.title_book = title_book;
        this.imgID = imgID;
    }


    public String getTitle_book() {
        return title_book;
    }

    public void setTitle_book(String title_book) {
        this.title_book = title_book;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
