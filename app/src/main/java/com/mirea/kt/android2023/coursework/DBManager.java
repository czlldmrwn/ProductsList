package com.mirea.kt.android2023.coursework;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBManager {

    private static final String TAG = "DBMamager";

    private SQLiteOpenHelper sqLiteHelper;

    public DBManager(SQLiteOpenHelper sqLiteHelper) {
        this.sqLiteHelper = sqLiteHelper;
    }

    public boolean saveProductToDatabase(Product product) {
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("product_name", product.getProductName());
        if (product.getPrice()!=0) {
            cv.put("price", product.getPrice());
        }
        if (product.getAmount()!=0) {
            cv.put("amount", product.getAmount());
        }
        long rowId = db.insert("TABLE_PRODUCTS", null, cv);
        cv.clear();
        db.close();
        Log.d(TAG, "saveProductToDatabase: Product Saved");
        return rowId != -1;
    }

    public ArrayList<Product> loadAllProductsFromDatabase() {
        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        Cursor dbCursor = db.query("TABLE_PRODUCTS", null, null,
                null, null, null, null);
        if (dbCursor.moveToFirst()) {
            do{
                String productName = dbCursor.getString(dbCursor.getColumnIndexOrThrow("product_name"));
                int price =  dbCursor.getInt(dbCursor.getColumnIndexOrThrow("price"));
                int amount =  dbCursor.getInt(dbCursor.getColumnIndexOrThrow("price"));
                products.add(new Product(productName, price, amount));
            } while (dbCursor.moveToNext());
        }
        dbCursor.close();
        db.close();
        Log.d(TAG, "loadAllProductsFromDatabase: Products Loaded");
        return products;
    }
}
