package com.example.th2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.th2.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SqliteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Chitieu.db";
    private static final int DATABASE_VERSION = 1;
    public SqliteHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "category TEXT," +
                "price TEXT," +
                "date TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion < 2) {
//            db.execSQL("DROP TABLE IF EXISTS items");
//        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    //get all order by data descending
    public List<Item> getAll(){
        List<Item> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "date DESC";
        Cursor rs = st.query("items",null,null,null,null,
                null,order);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String tittle = rs.getString(1);
            String category = rs.getString(2);
            String price = rs.getString(3);
            String date = rs.getString(4);
            list.add(new Item(id,tittle,category,price,date));
        }
        return list;
    }
    //add item
    public long addItem(Item item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",item.getTittle());
        contentValues.put("category",item.getCategory());
        contentValues.put("price",item.getPrice());
        contentValues.put("date",item.getDate());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("items",null,contentValues);
    }
    //get by date
    public List<Item> getByDate(String date){
        List<Item> list = new ArrayList<>();
        String where = "date LIKE ?";
        String[] whereAgrs = {date};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,where,whereAgrs,null,null,null);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String tittle = rs.getString(1);
            String category = rs.getString(2);
            String price = rs.getString(3);
            list.add(new Item(id,tittle,category,price,date));
        }
        return list;
    }
    //update
    public int update(Item item){
        ContentValues values = new ContentValues();
        values.put("title",item.getTittle());
        values.put("category",item.getCategory());
        values.put("price",item.getPrice());
        values.put("date",item.getDate());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereAgrs = {item.getId()+""};
        return sqLiteDatabase.update("items",values,whereClause,whereAgrs);
    }
    //delete
    public int delete(int id){
        String whereClause = "id=?";
        String[] whereAgrs = {id+""};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("items",whereClause,whereAgrs);
    }
    //search by key
    public List<Item> searchByTitle(String key){
        List<Item> list = new ArrayList<>();
        String where = "title LIKE ?";
        String[] whereAgrs = {"%"+key+"%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,where,whereAgrs,null,null,null);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String tittle = rs.getString(1);
            String category = rs.getString(2);
            String price = rs.getString(3);
            String date = rs.getString(4);
            list.add(new Item(id,tittle,category,price,date));
        }
        return list;
    }
    //search by category
    public List<Item> searchByCategory(String category){
        List<Item> list = new ArrayList<>();
        String where = "category LIKE ?";
        String[] whereAgrs = {category};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,where,whereAgrs,null,null,null);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String tittle = rs.getString(1);
            String c = rs.getString(2);
            String price = rs.getString(3);
            String date = rs.getString(4);
            list.add(new Item(id,tittle,c,price,date));
        }
        return list;
    }
    //search by date
    public List<Item> searchByDateFromTo(String from, String to){
        List<Item> list = new ArrayList<>();
        String where = "date BETWEEN ? AND ?";
        String[] whereAgrs = {from.trim(),to.trim()};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",null,where,whereAgrs,null,null,null);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String tittle = rs.getString(1);
            String c = rs.getString(2);
            String price = rs.getString(3);
            String date = rs.getString(4);
            list.add(new Item(id,tittle,c,price,date));
        }
        return list;
    }
}
