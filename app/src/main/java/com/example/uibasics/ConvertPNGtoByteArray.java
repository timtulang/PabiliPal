package com.example.uibasics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import androidx.annotation.NonNull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConvertPNGtoByteArray {
    public byte[] convertPNGToByteArray(@NonNull Context context, int pngResourceId) {
        // Open the PNG file input stream
        InputStream inputStream = context.getResources().openRawResource(pngResourceId);

        // Create a byte array output stream to hold the converted data
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // Read the PNG file into a Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            // Compress the Bitmap into a PNG format
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (Exception e) {
            Log.e("PNGHelper", "Error converting PNG to byte array: " + e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return outputStream.toByteArray();
    }
}
