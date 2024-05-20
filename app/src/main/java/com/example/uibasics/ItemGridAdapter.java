package com.example.uibasics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemGridAdapter extends BaseAdapter {

    Context context;
    String[] productNames;
    double[] productPrices;
    int[] productQuantities, images;
    boolean isSecondGridView;
    LayoutInflater inflater;

    private static final int CONSTANT_ITEM_TYPE = 1;
    private static final int REGULAR_ITEM_TYPE = 0;

    public ItemGridAdapter(Context context, String[] productNames, double[] productPrices, int[] productQuantities, int[] images, boolean isSecondGridView) {
        this.context = context;
        this.productNames = productNames;
        this.productPrices = productPrices;
        this.productQuantities = productQuantities;
        this.images = images;
        this.isSecondGridView = isSecondGridView;
    }

    @Override
    public int getCount() {
        if (isSecondGridView) {
            return productNames.length + 1; // Extra item for the constant item
        } else {
            return productNames.length;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isSecondGridView && position == productNames.length) {
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
        if (isSecondGridView && position == productNames.length) {
            return null; // Return null for the constant item
        } else {
            return productNames[position];
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
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

            imageView.setImageResource(images[position]);
            productName.setText(productNames[position]);
            productPrice.setText(String.valueOf(productPrices[position]));
            productQuantity.setText(String.valueOf(productQuantities[position]));
        }

        return convertView;
    }
}