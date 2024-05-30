package com.example.uibasics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingCart extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartItems> cartItems;
    private TextView totalPriceTextView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopping_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton imageButton2;
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ShoppingCart.this, ProductCart.class);
                startActivity(intent);
            }
        });

        Button confirmButton;
        confirmButton = findViewById(R.id.button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success = CartManager.getInstance(ShoppingCart.this).checkout(ShoppingCart.this);
                if (success) {
                    Intent intent = new Intent(ShoppingCart.this, CartSummary.class);
                    startActivity(intent);
                } else {
                    // Optionally, show a message to the user about the insufficient stock
                    Toast.makeText(ShoppingCart.this, "Insufficient stock for one or more items.", Toast.LENGTH_LONG).show();
                    // Return to the previous activity with the same cart values
                }
            }
        });

        recyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceTextView = findViewById(R.id.textView16);
        cartItems = CartManager.getInstance(this).getCartItems();

        cartAdapter = new CartAdapter(this, cartItems, totalPriceTextView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);

        double totalPrice = CartManager.getInstance(this).getTotalPrice();
        totalPriceTextView.setText("Total: ₱" + totalPrice);
    }
}

