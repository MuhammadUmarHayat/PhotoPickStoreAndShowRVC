package com.example.restaurant_management_app;
import android.graphics.Bitmap;

public class ImageModel {
    private Bitmap image;

    public ImageModel(Bitmap image)
    {
        this.image = image;
    }

    public Bitmap getImage()
    {
        return image;
    }
}
