package com.example.uibasics;

import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        String[] productName = {"Toothpaste", "Toothbrush", "Soap", "Shampoo"};
        double[] productPrice = {10.00, 25.5, 17, 25};
        int[] quantity = {10, 5, 12, 11};
        int [] images = {R.drawable.placeholder, R.drawable.placeholder, R.drawable.placeholder, R.drawable.placeholder};

        GridView gridView = findViewById(R.id.availableItems);

        ItemGridAdapter gridAdapter = new ItemGridAdapter(inventory.this, productName, productPrice, quantity, images, true);

        gridView.setAdapter(gridAdapter);
    }
}