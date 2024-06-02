package com.example.uibasics;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


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
        if (inflater == null){
            inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_before_cart, null);
        }

        ImageView imageView = convertView.findViewById(R.id.productImage);
        TextView productName = convertView.findViewById(R.id.productNameItems);
        TextView productPrice = convertView.findViewById(R.id.productPriceItems);
        TextView productQuantity = convertView.findViewById(R.id.productQuantityItems);
        Button button4 = convertView.findViewById(R.id.button4);

        CartItems product = productList.get(position);

        Bitmap imageBitmap = imageHelper.byteArrayToBitmap(product.getImagePath());

        imageView.setImageBitmap(imageBitmap);
        productName.setText(product.getName());
        productPrice.setText("₱" + product.getPrice());
        productQuantity.setText("Qty: " + product.getQuantity());

        button4.setOnClickListener(v -> {
            CartManager.getInstance(context).addToCart(product, 1);
            Toast.makeText(context, product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }

    public void updateProductList(List<CartItems> newProductList) {
        productList.clear();
        productList.addAll(newProductList);
        notifyDataSetChanged();
    }

}