package com.example.uibasics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uibasics.databinding.ActivityInventoryBinding;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Inventory extends AppCompatActivity {

    private static final int ADD_ITEM_REQUEST_CODE = 1;
    ActivityInventoryBinding binding;
    InventoryRepository inventoryRepository;
    GridView gridView;
    ArrayList<InventoryItem> itemsRetrieved;
    ItemGridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inventoryRepository = new InventoryRepository(Inventory.this);
        gridView = findViewById(R.id.availableItems);


        loadItems();

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent;
            try {
                InventoryItem selectedItem = itemsRetrieved.get(position);
                intent = new Intent(Inventory.this, EditItemInventory.class);
                intent.putExtra("selectedItem", selectedItem);
            } catch (IndexOutOfBoundsException e){
                intent = new Intent(Inventory.this, AddItemInventory.class);
            }

            startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItems();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            loadItems();
        }
    }

    private void loadItems() {
        itemsRetrieved = new ArrayList<>(inventoryRepository.getItems());
        ArrayList<String> productName = new ArrayList<>();
        ArrayList<Double> productPrice = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        List<byte[]> images = new ArrayList<>();


        for (InventoryItem item : itemsRetrieved) {
            productName.add(item.getName());
            productPrice.add(item.getPrice());
            quantity.add(item.getStock());
            images.add(item.getImage());
        }

        gridAdapter = new ItemGridAdapter(Inventory.this, productName, productPrice, quantity, images, true);
        gridView.setAdapter(gridAdapter);
    }


}
