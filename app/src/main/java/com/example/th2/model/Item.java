package com.example.th2.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String tittle,category, price,date;

    public Item(int id, String tittle, String category, String price, String date) {
        this.id = id;
        this.tittle = tittle;
        this.category = category;
        this.price = price;
        this.date = date;
    }

    public Item(String tittle, String category, String price, String date) {
        this.tittle = tittle;
        this.category = category;
        this.price = price;
        this.date = date;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
