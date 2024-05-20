package com.example.uibasics;
import android.content.Context;
import android.text.Layout;
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

    LayoutInflater inflater;

    public ItemGridAdapter(Context context, String[] productNames, double[] productPrices, int[] productQuantities, int[] images) {
        this.context = context;
        this.productNames = productNames;
        this.productPrices = productPrices;
        this.productQuantities = productQuantities;
        this.images = images;
    }

    @Override
    public int getCount() {
        return productNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null){
            inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.grid_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.productImage);
        TextView productName = convertView.findViewById(R.id.productNameItems);
        TextView productPrice = convertView.findViewById(R.id.productPriceItems);
        TextView productQuantity = convertView.findViewById(R.id.productQuantityItems);

        imageView.setImageResource(images[position]);
        productName.setText(productNames[position]);
        productPrice.setText(String.valueOf(productPrices[position]));
        productQuantity.setText(String.valueOf(productQuantities[position]));



        return convertView;
    }
}
