package com.example.bookdroidproject.model;

public class Booksmodel {
    private String title;
    private int img_book;

    public Booksmodel() {
    }

    public Booksmodel(String title, int img_book) {
        this.title = title;
        this.img_book = img_book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg_book() {
        return img_book;
    }

    public void setImg_book(int img_book) {
        this.img_book = img_book;
    }
}
