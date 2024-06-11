package com.example.uibasics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

public class ItemGridAdapter1 extends BaseAdapter {

    private Context context;
    private List<CartItems> productList;
    private LayoutInflater inflater;
    private ImageHelper imageHelper = new ImageHelper();

    public ItemGridAdapter1(Context context, List<CartItems> productList) {
        this.context = context;
        this.productList = productList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.productImage);
            holder.productName = convertView.findViewById(R.id.productNameItems);
            holder.productPrice = convertView.findViewById(R.id.productPriceItems);
            holder.productQuantity = convertView.findViewById(R.id.productQuantityItems);
            holder.cardView = convertView.findViewById(R.id.cardView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CartItems product = productList.get(position);
        Bitmap imageBitmap = imageHelper.byteArrayToBitmap(product.getImagePath());
        holder.imageView.setImageBitmap(imageBitmap);
        holder.productName.setText(product.getName());
        holder.productPrice.setText("₱" + product.getPrice());
        holder.productQuantity.setText("Qty: " + product.getQuantity());

        if (product.getQuantity() == 0) {
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.gray));
            holder.cardView.setOnClickListener(null);
            convertView.setOnClickListener(null);
        } else {
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            convertView.setOnClickListener(v -> {
                Intent intent = new Intent(context, AddItemToCart.class);
                intent.putExtra("selectedItem", product);
                context.startActivity(intent);
            });
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView productName;
        TextView productPrice;
        TextView productQuantity;
        CardView cardView;
    }

    public void updateProductList(List<CartItems> newProductList) {
        this.productList = new ArrayList<>(newProductList);
        notifyDataSetChanged();
    }
}
