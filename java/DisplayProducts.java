package com.example.restaurant_management_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DisplayProducts extends AppCompatActivity
{
DBMain dbMain;
    ArrayList<String> id,name;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);
        RecyclerView recyclerView = findViewById(R.id.rc1); // Replace with your RecyclerView ID
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ImageModel> imageList = new ArrayList<>();
        id=new ArrayList<>();
        name=new ArrayList<>();

        dbMain=new DBMain(this);
        SQLiteDatabase db = dbMain.getReadableDatabase();

        ImageAdapter imageAdapter = new ImageAdapter(imageList,name,id);


        Cursor cursor=dbMain.getAllProducts();
      //  Cursor cursor = db.query("products", new String[]{"photo"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
//            byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex("photo"));
            byte[] imageBytes = cursor.getBlob(1);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imageList.add(new ImageModel(bitmap));
            id.add(cursor.getString(0));
            name.add(cursor.getString(2));
        }
        cursor.close();
        db.close();
        recyclerView.setAdapter(imageAdapter);


    }
}