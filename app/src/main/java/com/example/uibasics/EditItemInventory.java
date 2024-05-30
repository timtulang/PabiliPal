package com.example.uibasics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;

public class EditItemInventory extends AppCompatActivity {

    int id;
    String name;
    double price = 0;
    int stock = 0;
    byte[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_item_inventory);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InventoryRepository inventory = new InventoryRepository(EditItemInventory.this);

        Intent intent = getIntent();
        InventoryItem selectedItem = (InventoryItem) intent.getSerializableExtra("selectedItem");

        ImageView addImage = findViewById(R.id.addImage);
        EditText productName = findViewById(R.id.productName);
        EditText productPrice = findViewById(R.id.productPrice);
        EditText addStock = findViewById(R.id.addStock);
        ImageButton decrementStock = findViewById(R.id.decrementStock);
        ImageButton incrementStock = findViewById(R.id.incrementStock);
        Button editItem = findViewById(R.id.addtoInventory);
        Button deleteItem = findViewById(R.id.deleteItem);

        if (selectedItem != null) {
            id = selectedItem.getId();
            name = selectedItem.getName();
            price = selectedItem.getPrice();
            stock = selectedItem.getStock();
            image = selectedItem.getImage();

            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            addImage.setImageBitmap(Bitmap.createBitmap(bitmap));
            productName.setText(name);
            productPrice.setText(String.valueOf(price));
            addStock.setText(String.valueOf(stock));
        }

        decrementStock.setOnClickListener(v -> {
            decreaseStock(addStock);
        });
        incrementStock.setOnClickListener(v -> {
            increaseStock(addStock);
        });

        editItem.setOnClickListener(v -> {
            String name = productName.getText().toString();
            double price = Double.parseDouble(productPrice.getText().toString());
            int stock = Integer.parseInt(addStock.getText().toString());
            byte[] imageToUpdate = image != null ? image : generatePlaceholderByteArray();

            inventory.updateItem(selectedItem.getId(), name, price, stock, imageToUpdate);
            finish();
        });

        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDeleteDialog confirmDelete = new ConfirmDeleteDialog(EditItemInventory.this);
                confirmDelete.show(new ConfirmDeleteDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        inventory.deleteItem(selectedItem.getId());
                        finish();
                    }

                });
            }
        });

    }

    private void decreaseStock(EditText addStock) {
        stock -= 1;
        addStock.setText(String.valueOf(stock));
    }

    private void increaseStock(EditText addStock) {
        stock += 1;
        addStock.setText(String.valueOf(stock));
    }

    private byte[] generatePlaceholderByteArray() {
        Bitmap placeholderBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        placeholderBitmap.eraseColor(android.graphics.Color.GRAY); // Fill with a solid color
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        placeholderBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}