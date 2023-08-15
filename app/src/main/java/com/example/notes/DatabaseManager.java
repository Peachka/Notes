package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context contexte) {
        this.context = context;

    }

    public DatabaseManager open() throws SQLiteException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
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

    public int update(long _id, String title, String lastModified, String created, String noteText){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TITLE, title);
        contentValues.put(DatabaseHelper.LAST_MODIFIED, lastModified);
        contentValues.put(DatabaseHelper.CREATED, created);
        contentValues.put(DatabaseHelper.NOTE_TEXT, noteText);
        int ret = database.update(DatabaseHelper.DATABASE_TABLE, contentValues, DatabaseHelper._ID + "=" + _id, null);
        return ret;
    }

    public void delete(long _id){
        database.delete(DatabaseHelper.DATABASE_TABLE,  DatabaseHelper._ID + "=" + _id, null);
    }


}
