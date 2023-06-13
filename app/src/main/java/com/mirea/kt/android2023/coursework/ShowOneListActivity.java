package com.mirea.kt.android2023.coursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

public class ShowOneListActivity extends AppCompatActivity implements ProductAdapter.OnProductClickListener{

    private static final String TAG = "ShowOneListActivity";
    private ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_one_list);
        Button btnShare = findViewById(R.id.btnShareList);
        Bundle args = getIntent().getExtras();
        if (args != null) {
            ProductsList productsList = (ProductsList) args.getSerializable("clicked_list");
            products = productsList.getProducts();
        }
        adapter = new ProductAdapter(products, this);
        RecyclerView rcView = findViewById(R.id.rvShowProducts);
        rcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcView.setAdapter(adapter);
        Log.i(TAG, "onCreate: Activity is created");

        btnShare.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra("список покупок", products.toString());
            startActivity(Intent.createChooser(intent, "Share with"));
        });
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onProductClick(Product product, int position){
        findViewById(R.id.productCardView).setBackgroundColor(R.color.red);
    }
}