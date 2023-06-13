package com.mirea.kt.android2023.coursework;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ViewHolder> {

    interface OnProductsListClickListener{
        void onProductsListClick(ProductsList productsList, int position);
    }
    private ArrayList<ProductsList> productsLists;
    private OnProductsListClickListener onProductsListClickListener;
    private static final String TAG = "ProductsListAdapter";

    public ProductsListAdapter(ArrayList<ProductsList> productsLists, OnProductsListClickListener onProductsListClickListener) {
        this.productsLists = productsLists;
        this.onProductsListClickListener = onProductsListClickListener;
        Log.i(TAG, "ProductsListAdapter: adapter is created");
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;

        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.tvListName);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products_list, parent,
                false);
        Log.i(TAG, "onCreateViewHolder: view holder is created");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductsList productsList = productsLists.get(position);
        holder.nameView.setText(productsList.getListName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProductsListClickListener.onProductsListClick(productsList, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsLists.size();
    }

    public void deleteList(int position){
        this.productsLists.remove(position);
        super.notifyItemRemoved(position);
    }

    public void renameList(String name, int position) {
        ProductsList productsList = this.productsLists.get(position);
        productsList.setListName(name);
    }
}

