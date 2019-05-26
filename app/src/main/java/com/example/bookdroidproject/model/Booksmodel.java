package com.example.bookdroidproject.model;

public class Booksmodel {

    private String title_book;
    private int img_book;

    public Booksmodel(String title_book, int img_book) {
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

    public int getImg_book() {
        return img_book;
    }

    public void setImg_book(int img_book) {
        this.img_book = img_book;
    }
}