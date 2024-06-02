package com.example.uibasics;

import java.io.Serializable;

public class CartItems implements Serializable {
    private String name;
    private double price;
    private int quantity;
    private byte[] imagePath;



    public CartItems(String name, double price, int quantity, byte[] imagePath) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public byte[] getImagePath() {
        return imagePath;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImagePath(byte[] imagePath) {
        this.imagePath = imagePath;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}