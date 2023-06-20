package com.example.restaurant_management_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity
{
    SQLiteDatabase db;
DBMain dbMain;
EditText name;
Button save,dispaly;
ImageView photo;


    private static final int PICK_IMAGE_REQUEST=100;
    private EditText imageDetailsET;
 //   private ImageView objectImageView;
    private Uri imageFilePath;
    private Bitmap imageStore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbMain=new DBMain(this);
        findViews();
        selectPhoto();
        insertData();

        displayProducts();
    }

    private void selectPhoto()
    {
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage(view);
            }
        });
    }

    private void displayProducts()
    {
        dispaly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(MainActivity.this, DisplayProducts.class);
                startActivity(page);

            }
        });
    }





    private void insertData()
    {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ContentValues cv = new ContentValues();
                    cv.put("photo", ImageViewToByte(photo));
                    cv.put("name", name.getText().toString());
                    db = dbMain.getWritableDatabase();
                    long inserted = db.insert("products", null, cv);
                    if (inserted > 0) {
                        Toast.makeText(MainActivity.this, "Record is saved", Toast.LENGTH_SHORT).show();
                      //  photo.setImageResource(R.mipmap.ic_launcher);
                     //   name.setText("");
                    }
                }
                catch (Exception exp)
                {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    name.setText(exp.getMessage().toString());
                }
            }
        });
    }

    private byte[] ImageViewToByte(ImageView photo)
    {
        Bitmap bitmap=((BitmapDrawable)photo.getDrawable()).getBitmap();
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[] bytes=stream.toByteArray();
        return bytes;
    }


    public void findViews()
    {

        name=findViewById(R.id.etPName);
        save=findViewById(R.id.btn1);
        dispaly=findViewById(R.id.btn2);
        photo=findViewById(R.id.photo1);
    }

    public void chooseImage(View objectView)
    {
        try
        {
            Intent objectIntent=new Intent();
            objectIntent.setType("image/*");
            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,PICK_IMAGE_REQUEST);

        }
        catch(Exception exp){

            Toast.makeText(this,exp.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        try{
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null &&data.getData()!=null)
            {
                imageFilePath=data.getData();
                imageStore= MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
                photo.setImageBitmap(imageStore);

            }


        }
        catch(Exception exp){

            Toast.makeText(this,exp.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }





}