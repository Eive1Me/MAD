package com.example.three_activity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_CITY, DatabaseHelper.COLUMN_GENDER, DatabaseHelper.COLUMN_SCORE};
        return  database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public List<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            @SuppressLint("Range") String city = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CITY));
            @SuppressLint("Range") int gender = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_GENDER));
            @SuppressLint("Range") int score = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_SCORE));
            int pic = R.drawable.mal;
            if (gender==0) pic = R.drawable.fem;
            users.add(new User(id, name, city, pic, score));
        }
        cursor.close();
        return  users;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }

    public User getUser(long id){
        User user = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            @SuppressLint("Range") String city = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CITY));
            @SuppressLint("Range") int gender = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_GENDER));
            @SuppressLint("Range") int score = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_SCORE));
            int pic = R.drawable.mal;
            if (gender==0) pic = R.drawable.fem;
            user = new User(id, name, city, pic, score);
        }
        cursor.close();
        return  user;
    }

    public long insert(User user){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, user.getName());
        cv.put(DatabaseHelper.COLUMN_CITY, user.getCity());
        if (user.getPicResource() == R.drawable.mal)
        cv.put(DatabaseHelper.COLUMN_GENDER, 1); else
        cv.put(DatabaseHelper.COLUMN_GENDER, 0);
        cv.put(DatabaseHelper.COLUMN_SCORE, user.getScore());

        return  database.insert(DatabaseHelper.TABLE, null, cv);
    }

    public long delete(long userId){
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(User user){
        String whereClause = DatabaseHelper.COLUMN_ID + "=" + user.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, user.getName());
        cv.put(DatabaseHelper.COLUMN_CITY, user.getCity());
        if (user.getPicResource() == R.drawable.mal)
            cv.put(DatabaseHelper.COLUMN_GENDER, 1); else
            cv.put(DatabaseHelper.COLUMN_GENDER, 0);
        cv.put(DatabaseHelper.COLUMN_SCORE, user.getScore());
        return database.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }
}