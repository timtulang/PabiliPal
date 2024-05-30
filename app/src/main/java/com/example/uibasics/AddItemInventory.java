package com.example.uibasics;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    ConvertPNGtoByteArray imgPlaceholder = new ConvertPNGtoByteArray();
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private static final int REQUEST_IMAGE_PICK = 102;
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
        ImageButton addImage = findViewById(R.id.addImage);
        EditText productName = findViewById(R.id.productName);
        EditText productPrice = findViewById(R.id.productPrice);
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



            if (validateInputs(prodName, prodPriceString, prodStockString)) {
                double prodPrice = Double.parseDouble(prodPriceString);
                int prodStock = Integer.parseInt(prodStockString);
                inventory.addItem(prodName, prodPrice, prodStock, generatePlaceholderByteArray());

                setResult(RESULT_OK);
                finish();
            }
        });
        addImage.setOnClickListener(v -> {
            ImageHelper.openImageChooser(AddItemInventory.this);
            if (itemImage != null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(itemImage, 0 , itemImage.length);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");

                itemImage = imageHelper.bitmapToByteArray(imageBitmap);

                Log.d("ImageDimensions", "Width: " + imageBitmap.getWidth() + ", Height: " + imageBitmap.getHeight());

                addImage.setImageBitmap(imageBitmap);
            } else if (requestCode == REQUEST_IMAGE_PICK) {
                Uri imageUri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    itemImage = imageHelper.inputStreamToByteArray(inputStream);

                    Bitmap imageBitmap = BitmapFactory.decodeStream(inputStream);

                    Log.d("ImageDimensions", "Width: " + imageBitmap.getWidth() + ", Height: " + imageBitmap.getHeight());
                    addImage.setImageBitmap(imageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private byte[] generatePlaceholderByteArray() {
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
