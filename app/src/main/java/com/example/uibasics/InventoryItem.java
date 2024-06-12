package com.example.uibasics;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class InventoryItem implements Serializable {
    private int id;
    private String name;
    private double price;
    private int stock;
    private byte[] image;

    private String desc;

    public InventoryItem(int id, String name, double price, int stock, byte[] image, String desc) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public byte[] getImage() {
        return image;
    }
    public String getDesc(){return desc;}

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: " + price + ", Stock: " + stock;
    }
}
