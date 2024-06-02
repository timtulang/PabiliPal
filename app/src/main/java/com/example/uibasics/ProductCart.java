package com.example.uibasics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ProductCart extends AppCompatActivity {

    private InventoryRepository dbHelper;
    private ItemGridAdapter1 gridAdapter;
    private GridView gridView;
    private SearchView searchView;
    private List<CartItems> productList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_products);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new InventoryRepository(this);

        Button button2;
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductCart.this, ShoppingCart.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton3;
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ProductCart.this, ShoppingCart.class);
                startActivity(intent);
            }
        });

        ImageButton backButton;
        backButton = findViewById(R.id.imageButton6);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ProductCart.this, MainActivity.class);
                startActivity(intent);
            }
        });


        gridView = findViewById(R.id.cartGridView);
        searchView = findViewById(R.id.searchView);
        loadProductsFromDatabase();
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false); // Expand the SearchView and focus on the input field
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterProducts(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterProducts(newText);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProductsFromDatabase();
    }

    private void loadProductsFromDatabase() {
        List<CartItems> productList = dbHelper.getAllProducts();
        gridAdapter = new ItemGridAdapter1(this, productList);
        gridView.setAdapter(gridAdapter);
    }

    private void filterProducts(String query) {
        List<CartItems> filteredList = dbHelper.searchProducts(query);
        gridAdapter.updateProductList(filteredList);
    }
}