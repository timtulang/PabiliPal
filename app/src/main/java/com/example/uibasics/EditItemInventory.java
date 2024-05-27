package com.example.uibasics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        setContentView(R.layout.activity_add_item_inventory);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        InventoryItem selectedItem = intent.getParcelableExtra("selectedItem");

        ImageView addImage = findViewById(R.id.addImage);
        EditText productName = findViewById(R.id.productName);
        EditText productPrice = findViewById(R.id.productPrice);
        EditText addStock = findViewById(R.id.addStock);
        ImageButton decrementStock = findViewById(R.id.decrementStock);
        ImageButton incrementStock = findViewById(R.id.incrementStock);

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

        decrementStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseStock();
            }
        });
        incrementStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseStock();
            }
        });

    }
    public void decreaseStock() {
        stock -= 1;
    }
    public void increaseStock() {
        stock += 1;
    }
}