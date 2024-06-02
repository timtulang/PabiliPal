package com.example.uibasics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddItemToCart extends AppCompatActivity {
    private ImageHelper imageHelper = new ImageHelper();
    private CartManager cartManager;
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_item_to_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize CartManager with application context to prevent memory leaks
        cartManager = CartManager.getInstance(getApplicationContext());

        Intent intent = getIntent();
        CartItems selectedItem = (CartItems) intent.getSerializableExtra("selectedItem");

        ImageView imageView = findViewById(R.id.addImage);
        TextView productName = findViewById(R.id.productName);
        TextView productPrice = findViewById(R.id.productPrice);
        EditText quantityText = findViewById(R.id.addStock);
        Button addtoCart = findViewById(R.id.addtoCart);
        ImageButton addQuantity = findViewById(R.id.incrementStock);
        ImageButton decreaseQuantity = findViewById(R.id.decrementStock);

        Bitmap imageBitmap = imageHelper.byteArrayToBitmap(selectedItem.getImagePath());

        if (selectedItem != null) {
            imageView.setImageBitmap(imageBitmap);
            productName.setText(selectedItem.getName());
            productPrice.setText(String.valueOf(selectedItem.getPrice()));
        }

        addQuantity.setOnClickListener(v -> incrementStock(quantityText));
        decreaseQuantity.setOnClickListener(v -> decrementStock(quantityText));

        addtoCart.setOnClickListener(v -> {
            cartManager.addToCart(selectedItem, quantity);
            Toast.makeText(this, selectedItem.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        });
    }

    private void incrementStock(EditText quantityText) {
        quantity += 1;
        quantityText.setText(String.valueOf(quantity));
    }

    private void decrementStock(EditText quantityText) {
        if (quantity == 0) {
            Toast.makeText(this, "Quantity cannot be less than 0", Toast.LENGTH_SHORT).show();
        } else {
            quantity -= 1;
            quantityText.setText(String.valueOf(quantity));
        }
    }
}
