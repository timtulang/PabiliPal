package com.example.uibasics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemGridAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> productNames;
    ArrayList<Double> productPrices;
    ArrayList<Integer> productQuantities;
    List<byte[]> images;
    boolean isSecondGridView;
    LayoutInflater inflater;

    private static final int CONSTANT_ITEM_TYPE = 1;
    private static final int REGULAR_ITEM_TYPE = 0;

    public ItemGridAdapter(Context context, ArrayList<String> productNames, ArrayList<Double> productPrices, ArrayList<Integer> productQuantities, List<byte[]> images, boolean isSecondGridView) {
        this.context = context;
        this.productNames = productNames;
        this.productPrices = productPrices;
        this.productQuantities = productQuantities;
        this.images = images;
        this.isSecondGridView = isSecondGridView;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (isSecondGridView) {
            return productNames.size() + 1; // Extra item for the constant item
        } else {
            return productNames.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isSecondGridView && position == productNames.size()) {
            return CONSTANT_ITEM_TYPE;
        } else {
            return REGULAR_ITEM_TYPE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2; // Two types: regular item and constant item
    }

    @Override
    public Object getItem(int position) {
        if (isSecondGridView && position == productNames.size()) {
            return null; // Return null for the constant item
        } else {
            return productNames.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Bitmap> convertBit(){
        List<Bitmap> bitmapList = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {
            byte[] byteArray = images.get(i);

            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            bitmapList.add(bitmap);
        }

        return bitmapList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (viewType == CONSTANT_ITEM_TYPE) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.inventoryaddbutton, parent, false);
            }
        } else {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.grid_item, parent, false);
            }

            ImageView imageView = convertView.findViewById(R.id.productImage);
            TextView productName = convertView.findViewById(R.id.productNameItems);
            TextView productPrice = convertView.findViewById(R.id.productPriceItems);
            TextView productQuantity = convertView.findViewById(R.id.productQuantityItems);
            CardView cardView = convertView.findViewById(R.id.cardView);

            imageView.setImageBitmap(convertBit().get(position));
            productName.setText(productNames.get(position));
            productPrice.setText(String.valueOf(productPrices.get(position)));
            productQuantity.setText(String.valueOf(productQuantities.get(position)));

            if (productQuantities.get(position) == 0) {
                cardView.setCardBackgroundColor(context.getResources().getColor(R.color.gray));
            } else {
                cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            }

        }

        return convertView;
    }
}