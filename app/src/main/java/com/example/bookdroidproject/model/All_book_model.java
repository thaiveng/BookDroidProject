package com.example.bookdroidproject.model;

public class All_book_model {
    private String title,cateType,author,pubDate;
    private int img_book;

    public All_book_model() {
    }

    public All_book_model(String title, String cateType, String author, String pubDate, int img_book) {
        this.title = title;
        this.cateType = cateType;
        this.author = author;
        this.pubDate = pubDate;
        this.img_book = img_book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCateType() {
        return cateType;
    }

    public void setCateType(String cateType) {
        this.cateType = cateType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public int getImg_book() {
        return img_book;
    }

    public void setImg_book(int img_book) {
        this.img_book = img_book;
    }
}
