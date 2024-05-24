package com.example.uibasics;

import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class inventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inventory);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InventoryRepository inventoryRepository = new InventoryRepository(inventory.this);

        ArrayList<InventoryItem> itemsRetrieved = new ArrayList<>(inventoryRepository.getItems());

        ArrayList<String> productName = new ArrayList<>();
        ArrayList<Double> productPrice = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        List<byte[]> images = new ArrayList<>();

        for (int i = 0; i < itemsRetrieved.size(); i++){
            productName.add(itemsRetrieved.get(i).getName());
            productPrice.add(itemsRetrieved.get(i).getPrice());
            quantity.add(itemsRetrieved.get(i).getStock());
            images.add(itemsRetrieved.get(i).getImage());
        }

        GridView gridView = findViewById(R.id.availableItems);

        ItemGridAdapter gridAdapter = new ItemGridAdapter(inventory.this, productName, productPrice, quantity, images, true);

        gridView.setAdapter(gridAdapter);
    }
}