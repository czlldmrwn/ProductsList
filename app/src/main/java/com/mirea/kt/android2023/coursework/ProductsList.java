package com.mirea.kt.android2023.coursework;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductsList implements Serializable {

    private String listName;

    private ArrayList<Product> products = new ArrayList<>();

    public ProductsList(String listName) {
        this.listName = listName;
    }

    public ProductsList(String listName, ArrayList<Product> products) {
        this.listName = listName;
        this.products = products;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getListName() {
        return listName;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
