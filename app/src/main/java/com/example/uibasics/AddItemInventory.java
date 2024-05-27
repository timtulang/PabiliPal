package com.example.uibasics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;

public class AddItemInventory extends AppCompatActivity {

    int stock = 0;
    InventoryRepository inventory = new InventoryRepository(AddItemInventory.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_inventory);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView addImage = findViewById(R.id.addImage);
        EditText productName = findViewById(R.id.productName);
        EditText productPrice = findViewById(R.id.productPrice);
        EditText addStock = findViewById(R.id.addStock);
        ImageButton decrementStock = findViewById(R.id.decrementStock);
        ImageButton incrementStock = findViewById(R.id.incrementStock);
        Button addItem = findViewById(R.id.addtoInventory);

        decrementStock.setOnClickListener(v -> decreaseStock(addStock));
        incrementStock.setOnClickListener(v -> increaseStock(addStock));

        final Bitmap bm = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);


        addItem.setOnClickListener(v -> {
            String prodName = productName.getText().toString();
            String prodPriceString = productPrice.getText().toString();
            String prodStockString = addStock.getText().toString();

            Log.d("AddItemInventory", "Add Item button clicked");


            if (validateInputs(prodName, prodPriceString, prodStockString)) {
                double prodPrice = Double.parseDouble(prodPriceString);
                int prodStock = Integer.parseInt(prodStockString);
                byte[] placeholderImage = generatePlaceholderByteArray();
                inventory.addItem(prodName, prodPrice, prodStock, placeholderImage);
                Log.d("validateInputs", "Add Item button clicked");

                setResult(RESULT_OK);
                finish();
            }
        });
    }
    public static byte[] generatePlaceholderByteArray() {
        Bitmap placeholderBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        placeholderBitmap.eraseColor(android.graphics.Color.GRAY); // Fill with a solid color
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        placeholderBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }


    private void decreaseStock(EditText addStock) {
        stock -= 1;
        addStock.setText(String.valueOf(stock));
    }

    private void increaseStock(EditText addStock) {
        stock += 1;
        addStock.setText(String.valueOf(stock));
    }

    private boolean validateInputs(String name, String price, String stock) {
        if (name.isEmpty() || price.isEmpty() || stock.isEmpty()) {
            // Handle the error, e.g., show a Toast message
            return false;
        }
        try {
            Double.parseDouble(price);
            Integer.parseInt(stock);
        } catch (NumberFormatException e) {

            return false;
        }
        return true;
    }
}
