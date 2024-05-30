package com.example.uibasics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<CartItems> cartItems;
    private TextView totalPriceTextView;
    private Context context;

    public CartAdapter(Context context, List<CartItems> cartItems, TextView totalPriceTextView) {
        this.context = context;
        this.cartItems = cartItems;
        this.totalPriceTextView = totalPriceTextView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.added_to_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CartItems product = cartItems.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText("₱" + product.getTotalPrice());
        holder.quantityEditText.setText(String.valueOf(product.getQuantity()));
        holder.imageView.setImageResource(R.drawable.noimg);

        holder.increaseButton.setOnClickListener(v -> {
            int newQuantity = product.getQuantity() + 1;
            product.setQuantity(newQuantity);
            holder.quantityEditText.setText(String.valueOf(newQuantity));
            CartManager.getInstance(context).updateQuantity(product, newQuantity);
            holder.priceTextView.setText("₱" + product.getTotalPrice());
            updateTotalPrice();
        });

        holder.decreaseButton.setOnClickListener(v -> {
            int newQuantity = product.getQuantity() - 1;
            if (newQuantity <= 0) {
                CartManager.getInstance(context).removeFromCart(product);
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), cartItems.size());
            } else {
                product.setQuantity(newQuantity);
                holder.quantityEditText.setText(String.valueOf(newQuantity));
                CartManager.getInstance(context).updateQuantity(product, newQuantity);
                holder.priceTextView.setText("₱" + product.getTotalPrice());
            }
            updateTotalPrice();
        });

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    private void updateTotalPrice() {
        double totalPrice = CartManager.getInstance(context).getTotalPrice();
        totalPriceTextView.setText("Total: ₱" + totalPrice);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView;
        EditText quantityEditText;
        ImageView imageView;
        Button increaseButton, decreaseButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView2);
            priceTextView = itemView.findViewById(R.id.textView3);
            quantityEditText = itemView.findViewById(R.id.editTextText);
            imageView = itemView.findViewById(R.id.imageView2);
            increaseButton = itemView.findViewById(R.id.button);
            decreaseButton = itemView.findViewById(R.id.button2);
        }
    }
}
