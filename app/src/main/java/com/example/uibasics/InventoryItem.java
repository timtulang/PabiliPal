package com.example.uibasics;

public class InventoryItem {
    private int id;
    private String name;
    private double price;
    private int stock;
    private byte[] image;

    public InventoryItem(int id, String name, double price, int stock, byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.image = image;
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

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: " + price + ", Stock: " + stock;
    }
}
