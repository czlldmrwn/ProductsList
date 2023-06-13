package com.mirea.kt.android2023.coursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowProductsListsActivity extends AppCompatActivity implements ProductsListAdapter.OnProductsListClickListener {

    private static final String TAG = "ShowProductsLists";
    private ProductsListAdapter adapter;

    private String newName;
    private int btnPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products_lists);
        Button addListBtn = findViewById(R.id.btnAddList);
        Button deleteListBtn = findViewById(R.id.btnDeleteList);
        Button renameListBtn = findViewById(R.id.btnRename);
        Button okNameBtn = findViewById(R.id.btnOkName);
        EditText etNewName = findViewById(R.id.etNewName);
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("молоко", 50, 1));
        ArrayList<ProductsList> productsLists = new ArrayList<>();
        productsLists.add(new ProductsList("список 1", products));
        productsLists.add(new ProductsList("пустой список"));
        Bundle args = getIntent().getExtras();
        if (args != null) {
            ProductsList newProductsList = (ProductsList) args.getSerializable("products_list");
            productsLists.add(newProductsList);
        }

        addListBtn.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: Add List Button Pressed");
            Intent addListIntent = new Intent(getApplicationContext(), AddListActivity.class);
            startActivity(addListIntent);
        });

        deleteListBtn.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: Delete List Button Pressed");
            btnPressed = 1;
            Toast.makeText(this, R.string.selectDeleteItem, Toast.LENGTH_LONG).show();
        });

        renameListBtn.setOnClickListener(v -> {
            Log.d(TAG, "OnCreate: Rename List Button Pressed");
            btnPressed = 2;
            etNewName.setVisibility(View.VISIBLE);
            okNameBtn.setVisibility(View.VISIBLE);
            Toast.makeText(this, R.string.enterNewName, Toast.LENGTH_LONG).show();
        });

        okNameBtn.setOnClickListener(v -> {
            newName = etNewName.getText().toString();
            Toast.makeText(this, R.string.selectRenameItem, Toast.LENGTH_LONG).show();
        });

        adapter = new ProductsListAdapter(productsLists, this);
        RecyclerView rcView = findViewById(R.id.rvProductsLists);
        rcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        rcView.setAdapter(adapter);
        Log.i(TAG, "onCreate: Activity is created");
    }

    private void DeleteList(int position){
        adapter.deleteList(position);
        adapter.notifyItemRemoved(position);
        btnPressed = 0;
    }

    private void RenameList(int position){
        String name = newName;
        adapter.renameList(name, position);
        adapter.notifyItemChanged(position);
        btnPressed = 0;
    }

    @Override
    public void onProductsListClick(ProductsList productsList, int position) {
        switch (btnPressed) {
            case 0:
                Intent showOneListIntent = new Intent(getApplicationContext(), ShowOneListActivity.class);
                showOneListIntent.putExtra("clicked_list", productsList);
                startActivity(showOneListIntent);
            case 1:
                DeleteList(position);
            case 2:
                RenameList(position);
        }
    }
}