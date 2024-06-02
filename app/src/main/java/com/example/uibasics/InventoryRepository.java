package com.example.uibasics;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;

public class InventoryRepository {
    private final DatabaseHelperInventory dbHelper;
    private final ExecutorService executorService;

    public InventoryRepository(Context context) {
        dbHelper = new DatabaseHelperInventory(context);
        executorService = Executors.newSingleThreadExecutor();
    }

    public void addItem(String name, double price, int stock, byte[] image) {
        executorService.execute(() -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelperInventory.COLUMN_NAME, name);
            values.put(DatabaseHelperInventory.COLUMN_PRICE, price);
            values.put(DatabaseHelperInventory.COLUMN_STOCK, stock);
            values.put(DatabaseHelperInventory.COLUMN_IMAGE, image);
            db.insert(DatabaseHelperInventory.TABLE_NAME, null, values);
            db.close();
        });
    }
    public void updateItem(int id, String name, double price, int stock, byte[] image) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelperInventory.COLUMN_NAME, name);
        values.put(DatabaseHelperInventory.COLUMN_PRICE, price);
        values.put(DatabaseHelperInventory.COLUMN_STOCK, stock);
        values.put(DatabaseHelperInventory.COLUMN_IMAGE, image);

        String whereClause = DatabaseHelperInventory.COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(id)};

        db.update(DatabaseHelperInventory.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }
    public void deleteItem(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = DatabaseHelperInventory.COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(id)};

        db.delete(DatabaseHelperInventory.TABLE_NAME, whereClause, whereArgs);
        db.close();
    }
    public List<InventoryItem> getItems() {
        List<InventoryItem> items = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelperInventory.COLUMN_ID,
                DatabaseHelperInventory.COLUMN_NAME,
                DatabaseHelperInventory.COLUMN_PRICE,
                DatabaseHelperInventory.COLUMN_STOCK,
                DatabaseHelperInventory.COLUMN_IMAGE
        };

        Cursor cursor = db.query(
                DatabaseHelperInventory.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelperInventory.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperInventory.COLUMN_NAME));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelperInventory.COLUMN_PRICE));
                int stock = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelperInventory.COLUMN_STOCK));
                byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelperInventory.COLUMN_IMAGE));

                InventoryItem item = new InventoryItem(id, name, price, stock, image);
                items.add(item);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return items;
    }
    public void addItemToCart(CartItems product) {
        SQLiteDatabase DB = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.getName());
        contentValues.put("price", product.getPrice());
        contentValues.put("stock", product.getQuantity());
        contentValues.put("image", product.getImagePath());
        DB.insertWithOnConflict("Cart", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public boolean updateProductQuantity(String name, int newQuantity) {
        SQLiteDatabase DB = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("stock", newQuantity);
        int rowsAffected = DB.update("Inventory", contentValues, "name=?", new String[]{name});
        return rowsAffected > 0;
    }

    public List<CartItems> getAllProducts() {
        List<CartItems> productList = new ArrayList<>();
        SQLiteDatabase DB = dbHelper.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Inventory", null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("stock"));
                byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));
                productList.add(new CartItems(name, price, quantity, image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productList;
    }

    public void shutDown() {
        executorService.shutdown();
    }

    public List<CartItems> searchProducts(String query) {
        List<CartItems> products = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Inventory WHERE name LIKE ?", new String[]{"%" + query + "%"});

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("stock"));
                byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));
                products.add(new CartItems(name, price, quantity, image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }

    public List<InventoryItem> searchProductsInventory(String query) {
        List<InventoryItem> products = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Inventory WHERE name LIKE ?", new String[]{"%" + query + "%"});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                int stock = cursor.getInt(cursor.getColumnIndexOrThrow("stock"));
                byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));
                products.add(new InventoryItem(id, name, price, stock, image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }

}
