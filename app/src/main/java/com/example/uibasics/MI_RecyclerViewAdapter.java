package com.example.uibasics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MI_RecyclerViewAdapter extends RecyclerView.Adapter<MI_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList <MenuItemModel> menuItemModels;
    public MI_RecyclerViewAdapter(Context context, ArrayList<MenuItemModel> menuItemModels){
        this.context = context;
        this.menuItemModels = menuItemModels;
    }
    @NonNull
    @Override
    public MI_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.menuviewrow, parent, false);
        return new MI_RecyclerViewAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MI_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(menuItemModels.get(position).getItemName());
        holder.tvDesc.setText(menuItemModels.get(position).getItemDescription());
        holder.imageView.setImageResource(menuItemModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {

        return menuItemModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvName;
        TextView tvDesc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.menuItemTitle);
            tvDesc = itemView.findViewById(R.id.description);

        }
    }
}
