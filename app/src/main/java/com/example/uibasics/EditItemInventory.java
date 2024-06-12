package com.example.uibasics;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class EditItemInventory extends AppCompatActivity {

    int id;
    String name;
    double price = 0;
    int stock = 0;
    byte[] image;
    String desc;
    ImageHelper imageHelper = new ImageHelper();
    private static final int REQUEST_IMAGE_CAPTURE = ImageHelper.REQUEST_IMAGE_CAPTURE;
    private static final int REQUEST_IMAGE_PICK = ImageHelper.REQUEST_IMAGE_PICK;
    ImageView addImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_item_inventory);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton backButtonEditItem;
        backButtonEditItem = findViewById(R.id.imageButton5);
        backButtonEditItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(EditItemInventory.this, Inventory.class);
                startActivity(intent);
            }
        });

        InventoryRepository inventory = new InventoryRepository(EditItemInventory.this);

        Intent intent = getIntent();
        InventoryItem selectedItem = (InventoryItem) intent.getSerializableExtra("selectedItem");

        addImage = findViewById(R.id.addImage);
        EditText productName = findViewById(R.id.productName);
        EditText productPrice = findViewById(R.id.productPrice);
        EditText productDesc = findViewById(R.id.productDesc);
        EditText addStock = findViewById(R.id.addStock);
        ImageButton decrementStock = findViewById(R.id.decrementStock);
        ImageButton incrementStock = findViewById(R.id.incrementStock);
        Button editItem = findViewById(R.id.addtoInventory);
        Button deleteItem = findViewById(R.id.deleteItem);

        if (selectedItem != null) {
            id = selectedItem.getId();
            name = selectedItem.getName();
            price = selectedItem.getPrice();
            stock = selectedItem.getStock();
            image = selectedItem.getImage();
            desc = selectedItem.getDesc();


            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            addImage.setImageBitmap(Bitmap.createBitmap(bitmap));
            productName.setText(name);
            productPrice.setText(String.valueOf(price));
            addStock.setText(String.valueOf(stock));
            productDesc.setText(String.valueOf(desc));
        }

        decrementStock.setOnClickListener(v -> {
            decreaseStock(addStock);
        });
        incrementStock.setOnClickListener(v -> {
            increaseStock(addStock);
        });

        editItem.setOnClickListener(v -> {
            String name = productName.getText().toString();
            double price = Double.parseDouble(productPrice.getText().toString());
            int stock = Integer.parseInt(addStock.getText().toString());
            String desc = productDesc.getText().toString();

            inventory.updateItem(selectedItem.getId(), name, price, stock, image != null ? image : generatePlaceholderByteArray(), desc);
            setResult(RESULT_OK);
            finish();
        });

        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDeleteDialog confirmDelete = new ConfirmDeleteDialog(EditItemInventory.this);
                confirmDelete.show(new ConfirmDeleteDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        inventory.deleteItem(selectedItem.getId());
                        finish();
                    }

                });
            }
        });

        addImage.setOnClickListener(v -> ImageHelper.openImageChooser(EditItemInventory.this));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Log.e("EditItemInventory", "Intent data is null");
                return;
            }

            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                if (imageBitmap != null) {
                    try {
                        // Check Bitmap dimensions
                        Log.d("EditItemInventory", "Image Bitmap Width: " + imageBitmap.getWidth() + ", Height: " + imageBitmap.getHeight());

                        // Resize Bitmap
                        Bitmap resizedBitmap = imageHelper.resizeBitmap(imageBitmap, addImage.getWidth(), addImage.getHeight());
                        addImage.setImageBitmap(resizedBitmap);
                        image = imageHelper.bitmapToByteArray(resizedBitmap);
                    } catch (Exception e) {
                        Log.e("EditItemInventory", "Error processing Bitmap: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    Log.e("EditItemInventory", "Image Bitmap is null");
                }
            } else if (requestCode == REQUEST_IMAGE_PICK) {
                Uri imageUri = data.getData();
                if (imageUri == null) {
                    Log.e("EditItemInventory", "Image URI is null");
                    return;
                }

                try (InputStream inputStream = getContentResolver().openInputStream(imageUri)) {
                    Bitmap imageBitmap = BitmapFactory.decodeStream(inputStream);
                    if (imageBitmap != null) {
                        try {
                            // Check Bitmap dimensions
                            Log.d("EditItemInventory", "Image Bitmap Width: " + imageBitmap.getWidth() + ", Height: " + imageBitmap.getHeight());

                            // Resize Bitmap
                            Bitmap resizedBitmap = imageHelper.resizeBitmap(imageBitmap, addImage.getWidth(), addImage.getHeight());
                            addImage.setImageBitmap(resizedBitmap);
                            image = imageHelper.bitmapToByteArray(resizedBitmap);
                        } catch (Exception e) {
                            Log.e("EditItemInventory", "Error processing Bitmap: " + e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("EditItemInventory", "Image Bitmap is null");
                    }
                } catch (IOException e) {
                    Log.e("EditItemInventory", "Error opening InputStream: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
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

    private byte[] generatePlaceholderByteArray() {
        Bitmap placeholderBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        placeholderBitmap.eraseColor(android.graphics.Color.GRAY); // Fill with a solid color
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        placeholderBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}