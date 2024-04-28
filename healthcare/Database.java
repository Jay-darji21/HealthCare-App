package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // SQL queries
    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "CREATE TABLE users(username text,email text,password text)";
        db.execSQL(qry1);

        String qry2 = "CREATE TABLE cart(username text,product text,price float,otype text)";
        db.execSQL(qry2);

        String qry3 = "CREATE TABLE orderPlace(username TEXT, fullname TEXT, address TEXT, pincode TEXT, contact TEXT, otype TEXT)";
        db.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
  //  Insert the data of registered users
    public void register(String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }

    // Login activity , check the user exist or not
    public int login(String username , String password){
        String str[] = new String[2];
        str[0]=username;
        str[1]=password;
        int result=0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username = ? and password = ?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }

    // Add items in cart
    public void addToCart(String username, String product,float price,String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }

    // View cart method
    public int checkCart(String username,String product){
        int result = 0;
        String str[] = new String[2];
        str[0]=username;
        str[1]=product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username=? and product = ?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

    // Remove the data from database
    public void removeCart(String username,String otype){
        String str[] = new String[2];
        str[0]=username;
        str[1]=otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart","username=? and otype=?",str);
        db.close();

    }

    // Fetch the data from database
    public ArrayList getCartData(String username, String otype){
        ArrayList<String> arr = new ArrayList();
        String str[] = new String[2];
        str[0]=username;
        str[1]=otype;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?",str);
        if(c.moveToFirst()){
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product+"$"+price);
            }while (c.moveToNext());
        }
        db.close();
        return arr;

    }

    // Add orders in database
    public void addOrder(String username,String fullname , String address , String pincode , String contact , String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("pincode",pincode);
        cv.put("contact",contact);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderPlace",null,cv);
        db.close();

    }

    // Get the order data from database

    public ArrayList getOrders(String username){
        ArrayList<String> arr = new ArrayList();
        String str[] = new String[1];
        str[0]=username;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderPlace where username = ?",str);
        if(c.moveToFirst()){
            do {
                String username1 = c.getString(1);
                String fullname = c.getString(2);
                String address = c.getString(3);
                String pincode = c.getString(4);
                String contact = c.getString(5);
                String otype = c.getString(6);
                arr.add(username1+"$"+fullname+"$"+address+"$"+pincode+"$"+contact+"$"+otype);
            }while (c.moveToNext());
        }
        db.close();
        return arr;

    }


}
