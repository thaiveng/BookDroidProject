package com.example.bookdroidproject.model;

public class Booksmodel {

    private String title_book;
    private String img_book;

    public Booksmodel(String title_book, String img_book) {
        this.title_book = title_book;
        this.img_book = img_book;
    }

    public Booksmodel() {
    }

    public String getTitle_book() {
        return title_book;
    }

    public void setTitle_book(String title_book) {
        this.title_book = title_book;
    }

    public String getImg_book() {
        return img_book;
    }

    public void setImg_book(String img_book) {
        this.img_book = img_book;
    }
}