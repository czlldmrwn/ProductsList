package com.mirea.kt.android2023.coursework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    interface OnProductClickListener{
        void onProductClick(Product product, int position);
    }
    private ArrayList<Product> products;

    public ProductAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    private ProductAdapter.OnProductClickListener onProductClickListener;

    public ProductAdapter(ArrayList<Product> products, OnProductClickListener onProductClickListener) {
        this.products = products;
        this.onProductClickListener = onProductClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView priceView;
        private final TextView amountView;

        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.tvProductName);
            priceView = view.findViewById(R.id.tvPrice);
            amountView = view.findViewById(R.id.tvAmount);
        }
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.nameView.setText(String.format("%s", product.getProductName()));
        if (product.getPrice() !=0){
        holder.priceView.setText(String.format("%d руб", product.getPrice()));}
        if (product.getAmount() !=0){
        holder.amountView.setText(String.format("%d шт", product.getAmount()));}
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProductClickListener.onProductClick(product, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
