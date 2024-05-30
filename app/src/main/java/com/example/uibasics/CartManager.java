package com.example.uibasics;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItems> cartItems;
    private List<CartItems> checkedOutItems;
    private double totalPrice;
    private InventoryRepository dbHelper;

    private CartManager(Context context) {
        cartItems = new ArrayList<>();
        checkedOutItems = new ArrayList<>();
        totalPrice = 0.00;
        dbHelper = new InventoryRepository(context);
    }

    public static synchronized CartManager getInstance(Context context) {
        if (instance == null) {
            instance = new CartManager(context);
        }
        return instance;
    }

    public void addToCart(CartItems product, int quantity) {
        boolean itemAlreadyExists = false;
        for (CartItems item : cartItems) {
            if (item.getName().equals(product.getName())) {
                item.setQuantity(item.getQuantity() + quantity);
                itemAlreadyExists = true;
                break;
            }
        }
        if (!itemAlreadyExists) {
            CartItems newProduct = new CartItems(product.getName(), product.getPrice(), quantity, product.getImagePath());
            cartItems.add(newProduct);
        }
        dbHelper.addItemToCart(product);
        recalculateTotalPrice();
    }

    public List<CartItems> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void recalculateTotalPrice() {
        totalPrice = 0.00;
        for (CartItems item : cartItems) {
            totalPrice += item.getTotalPrice();
        }
    }

    public void updateQuantity(CartItems product, int newQuantity) {
        if (newQuantity <= 0) {
            removeFromCart(product);
        } else {
            for (CartItems item : cartItems) {
                if (item.getName().equals(product.getName())) {
                    item.setQuantity(newQuantity);
                    break;
                }
            }
        }
        recalculateTotalPrice();
    }

    public void removeFromCart(CartItems product) {
        cartItems.remove(product);
        recalculateTotalPrice();
    }

    public void clearCart() {
        cartItems.clear();
        recalculateTotalPrice();
    }

    public List<CartItems> getCheckedOutItems() {
        return checkedOutItems;
    }

    public boolean checkout(Context context) {
        DatabaseHelperInventory dbHelper = new DatabaseHelperInventory(context);
        InventoryRepository cartOperations = new InventoryRepository(context);
        for (CartItems product : cartItems) {
            Cursor cursor = dbHelper.getReadableDatabase().rawQuery("SELECT stock FROM Inventory WHERE name = ?", new String[]{product.getName()});
            if (cursor.moveToFirst()) {
                int currentStock = cursor.getInt(cursor.getColumnIndexOrThrow("stock"));
                if (currentStock < product.getQuantity()) {
                    // Notify user or log the issue
                    System.out.println("Insufficient stock for product: " + product.getName());
                    cursor.close();
                    return false;  // Cancel the checkout process
                }
                int newStock = currentStock - product.getQuantity();
                cartOperations.updateProductQuantity(product.getName(), newStock);
            }
            cursor.close();
        }
        checkedOutItems.clear();
        checkedOutItems.addAll(cartItems);
        clearCart();
        return true;  // Checkout successful
    }
}
