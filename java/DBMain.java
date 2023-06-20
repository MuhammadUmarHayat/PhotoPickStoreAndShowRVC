package com.example.restaurant_management_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBMain extends SQLiteOpenHelper
{
 public static final String DBNAME="resDB1.db";
 public static final String TABLENAME="products";
 public static final int VERSION=25;
    public DBMain(@Nullable Context context)
    {
        //create database
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
String query="create table products(id integer primary key AUTOINCREMENT, photo blob, name text)";
sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
String query="drop table if exists products";
db.execSQL(query);
    }
    public Cursor getAllProducts()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from products", null);
        return res;
    }
}
