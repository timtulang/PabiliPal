package com.example.uibasics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartSummary extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartSummaryAdapter cartSummaryAdapter;
    private List<CartItems> cartItems;
    private TextView totalPriceSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton imageButton4;
        imageButton4 = findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(CartSummary.this, ProductCart.class);
                startActivity(intent);
            }
        });

        Button button3;
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(CartSummary.this, ProductCart.class);
                startActivity(intent);
            }
        });

        totalPriceSummary = findViewById(R.id.totalPriceSummary);
        recyclerView = findViewById(R.id.cartSummaryRecyclerView);
        cartItems = CartManager.getInstance(this).getCheckedOutItems();

        cartSummaryAdapter = new CartSummaryAdapter(cartItems, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartSummaryAdapter);

        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double totalPrice = 0.00;
        for (CartItems product : cartItems) {
            totalPrice += product.getTotalPrice();
        }
        totalPriceSummary.setText("Total: ₱" + totalPrice);
    }

}