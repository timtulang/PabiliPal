package com.example.uibasics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddItemInventory extends AppCompatActivity {

    int stock = 0;
    InventoryRepository inventory = new InventoryRepository(AddItemInventory.this);
    ImageHelper imageHelper = new ImageHelper();
    private static final int REQUEST_IMAGE_CAPTURE = ImageHelper.REQUEST_IMAGE_CAPTURE;
    private static final int REQUEST_IMAGE_PICK = ImageHelper.REQUEST_IMAGE_PICK;
    byte[] itemImage;
    ImageButton addImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_inventory);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addImage = findViewById(R.id.addImage);
        ImageButton backButtonAddItem;
        backButtonAddItem = findViewById(R.id.imageButton7);
        backButtonAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(AddItemInventory.this, Inventory.class);
                startActivity(intent);
            }
        });

        ImageButton addImage = findViewById(R.id.addImage);
        EditText productName = findViewById(R.id.productName);
        EditText productPrice = findViewById(R.id.productPrice);
        EditText productDesc = findViewById(R.id.productDesc);
        EditText addStock = findViewById(R.id.addStock);
        ImageButton decrementStock = findViewById(R.id.decrementStock);
        ImageButton incrementStock = findViewById(R.id.incrementStock);
        Button addItem = findViewById(R.id.addtoInventory);

        decrementStock.setOnClickListener(v -> decreaseStock(addStock));
        incrementStock.setOnClickListener(v -> increaseStock(addStock));

        addItem.setOnClickListener(v -> {
            String prodName = productName.getText().toString();
            String prodPriceString = productPrice.getText().toString();
            String prodStockString = addStock.getText().toString();
            String prodDesc = productDesc.getText().toString();

            if (validateInputs(prodName, prodPriceString, prodStockString)) {
                double prodPrice = Double.parseDouble(prodPriceString);
                int prodStock = Integer.parseInt(prodStockString);
                inventory.addItem(prodName, prodPrice, prodStock, itemImage != null ? itemImage : generatePlaceholderByteArray(this), prodDesc);

                setResult(RESULT_OK);
                finish();
            }
        });

        addImage.setOnClickListener(v -> ImageHelper.openImageChooser(AddItemInventory.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    if (imageBitmap != null) {
                        Bitmap resizedBitmap = imageHelper.resizeBitmap(imageBitmap, addImage.getWidth(), addImage.getHeight());
                        addImage.setImageBitmap(resizedBitmap);
                        itemImage = imageHelper.bitmapToByteArray(resizedBitmap);
                    } else {
                        Log.e("AddItemInventory", "Image Bitmap is null");
                    }
                } else {
                    Log.e("AddItemInventory", "Extras are null");
                }
            } else if (requestCode == REQUEST_IMAGE_PICK) {
                Uri imageUri = data.getData();
                if (imageUri != null) {
                    try (InputStream inputStream = getContentResolver().openInputStream(imageUri)) {
                        Bitmap imageBitmap = BitmapFactory.decodeStream(inputStream);
                        if (imageBitmap != null) {
                            Bitmap resizedBitmap = imageHelper.resizeBitmap(imageBitmap, addImage.getWidth(), addImage.getHeight());
                            addImage.setImageBitmap(resizedBitmap);
                            itemImage = imageHelper.bitmapToByteArray(resizedBitmap);
                        } else {
                            Log.e("AddItemInventory", "Image Bitmap is null");
                        }
                    } catch (IOException e) {
                        Log.e("AddItemInventory", "Error opening InputStream: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    Log.e("AddItemInventory", "Image URI is null");
                }
            }
        } else {
            Log.e("AddItemInventory", "Intent data is null or result is not OK");
        }
    }

    private byte[] generatePlaceholderByteArray(Context context) {
        // Load the drawable resource
        Drawable drawable = context.getResources().getDrawable(R.drawable.noimage);

        // Create a bitmap with the same dimensions as the drawable
        Bitmap placeholderBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // Create a canvas to draw the drawable onto the bitmap
        Canvas canvas = new Canvas(placeholderBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        // Compress the bitmap to a byte array
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        placeholderBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void decreaseStock(EditText addStock) {
        if (stock == 0){
            Toast.makeText(this, "Stock cannot be less than 0", Toast.LENGTH_SHORT).show();
        } else {
            stock -= 1;
            addStock.setText(String.valueOf(stock));
        }
    }

    private void increaseStock(EditText addStock) {
        stock += 1;
        addStock.setText(String.valueOf(stock));
    }

    private boolean validateInputs(String name, String price, String stock) {
        if (name.isEmpty() || price.isEmpty() || stock.isEmpty()) {
            Toast.makeText(AddItemInventory.this, "Please fill out the fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        try {
            Double.parseDouble(price);
            Integer.parseInt(stock);
        } catch (NumberFormatException e) {
            Toast.makeText(AddItemInventory.this, "Invalid input format", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}