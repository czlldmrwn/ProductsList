package com.mirea.kt.android2023.coursework;

import java.io.Serializable;

public class Product implements Serializable {

    private String productName;
    private int price;
    private int amount;

    public Product(String productName) {
        this.productName = productName;
    }

    public Product(String productName, int price, int amount) {
        this.productName = productName;
        this.price = price;
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

