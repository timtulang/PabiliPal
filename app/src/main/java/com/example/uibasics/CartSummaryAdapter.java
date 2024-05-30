package com.example.uibasics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class CartSummaryAdapter extends RecyclerView.Adapter<CartSummaryAdapter.ViewHolder> {

    private List<CartItems> cartItems;
    private TextView totalPriceSummary;

    public CartSummaryAdapter(List<CartItems> cartItems, TextView totalPriceSummary) {
        this.cartItems = cartItems;
        this.totalPriceSummary = totalPriceSummary;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_summary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CartItems product = cartItems.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText("₱" + product.getTotalPrice());
        holder.quantityTextView.setText("Quantity: " + product.getQuantity());
        holder.imageView.setImageResource(R.drawable.noimg);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, quantityTextView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView6);
            priceTextView = itemView.findViewById(R.id.textView8);
            quantityTextView = itemView.findViewById(R.id.textView7);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
