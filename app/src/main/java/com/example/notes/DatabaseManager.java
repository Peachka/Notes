package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.ContactsContract;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public DatabaseManager open() throws SQLiteException {
        dbHelper = new DatabaseHelper(context);
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert(String title, String lastModified, String created, String noteText){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TITLE, title);
        contentValues.put(DatabaseHelper.LAST_MODIFIED, lastModified);
        contentValues.put(DatabaseHelper.CREATED, created);
        contentValues.put(DatabaseHelper.NOTE_TEXT, noteText);
        database.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);

    }

    public Cursor fetch(){
        String[] columns = new String[]{DatabaseHelper._ID,DatabaseHelper.TITLE, DatabaseHelper.LAST_MODIFIED, DatabaseHelper.CREATED,DatabaseHelper.NOTE_TEXT };
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, columns, null, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update( String title, String lastModified, String created, String noteText){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TITLE, title);
        contentValues.put(DatabaseHelper.LAST_MODIFIED, lastModified);
        contentValues.put(DatabaseHelper.NOTE_TEXT, noteText);
        int ret = database.update(DatabaseHelper.DATABASE_TABLE, contentValues, DatabaseHelper.CREATED + "=?" , new String[]{created});
        return ret;
    }

    public void delete(String created){
        database.delete(DatabaseHelper.DATABASE_TABLE,  DatabaseHelper.CREATED + "=?" , new String[]{created});
    }

    public boolean noteAlreadyExist(String created){
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, new String[]{DatabaseHelper.CREATED}, DatabaseHelper.CREATED + "=?" , new String[]{created}, null, null, null );

        boolean exists = cursor != null && cursor.moveToFirst();
        if (cursor != null) {
            cursor.close();
        }
        return exists;
    }
}
