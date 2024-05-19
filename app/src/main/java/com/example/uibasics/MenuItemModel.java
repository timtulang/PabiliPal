package com.example.uibasics;

public class MenuItemModel {
    String itemName;
    String itemDescription;
    int image;


    public MenuItemModel(String itemName, String itemDescription, int image) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.image = image;
    }


    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getImage() {
        return image;
    }
}
