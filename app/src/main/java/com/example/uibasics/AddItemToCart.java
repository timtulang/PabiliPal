package com.example.uibasics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
    private int quantity = 0;
    private int availableStock = 0;

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
            availableStock = selectedItem.getQuantity();
        }

        ImageButton backButton;
        backButton = findViewById(R.id.imageButton6);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(AddItemToCart.this, ProductCart.class);
                startActivity(intent);
            }
        });

        addQuantity.setOnClickListener(v -> incrementStock(quantityText));
        decreaseQuantity.setOnClickListener(v -> decrementStock(quantityText));

        quantityText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (!input.isEmpty()) {
                    try {
                        quantity = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        quantity = 0;
                    }
                } else {
                    quantity = 0;
                }
            }
        });

        addtoCart.setOnClickListener(v -> {
            if (quantity <= 0) {
                Toast.makeText(this, "Quantity must be greater than 0", Toast.LENGTH_SHORT).show();
            } else if (quantity > availableStock) {
                Toast.makeText(this, "Insufficient stock available", Toast.LENGTH_SHORT).show();
            } else {
                cartManager.addToCart(selectedItem, quantity);
                Toast.makeText(this, selectedItem.getName() + " added to cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void incrementStock(EditText quantityText) {
        if (quantity < availableStock) {
            quantity += 1;
            quantityText.setText(String.valueOf(quantity));
        } else {
            Toast.makeText(this, "Cannot exceed available stock", Toast.LENGTH_SHORT).show();
        }
    }

    private void decrementStock(EditText quantityText) {
        if (quantity > 0) {
            quantity -= 1;
            quantityText.setText(String.valueOf(quantity));
        } else {
            Toast.makeText(this, "Quantity cannot be less than 0", Toast.LENGTH_SHORT).show();
        }
    }
}
