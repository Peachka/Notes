package com.example.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "my_notes.db";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_TABLE = "notes";
    static final String _ID = "_ID";
    static final String TITLE = "title";
    static final String LAST_MODIFIED = "last_modified";
    static final String CREATED = "created";
    static final String NOTE_TEXT = "note_text";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TITLE + " TEXT, " +
            LAST_MODIFIED + " TEXT, " +
            CREATED + " TEXT, " +
            NOTE_TEXT + " TEXT);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

    }
}
