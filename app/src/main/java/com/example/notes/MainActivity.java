package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{
    ArrayList<NotesModel> notesModels = new ArrayList<>();
    DatabaseManager dbManager;
    ArrayList<String> notesTitles = new ArrayList<>();
    ArrayList<String> notesShortcuts = new ArrayList<>();
    ArrayList<String> modifiedTime = new ArrayList<>();
    ArrayList<String> createdTime = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycrerView);
        ImageView addNote = findViewById(R.id.addNote);

        dbManager = new DatabaseManager(this);

        try {
            dbManager.open();
        }catch (Exception e){
            e.printStackTrace();
        }

        setNotesModels();

        NotesRecyclerViewAdapter adapter = new NotesRecyclerViewAdapter(this, notesModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("MODIFIED", MainActivity2.getTime());
                intent.putExtra("CREATED", MainActivity2.getTime());
                startActivity(intent);
            }
        });

    }

    private void setNotesModels(){
        //notesTitles = new ArrayList<>(Arrays.asList("Title1", "Title2", "Title3"));
        //notesShortcuts = new ArrayList<>(Arrays.asList("Note1", "Note2", "Note3"));
        fetchData();

        for(int i = 0; i < notesShortcuts.size(); i++){
            notesModels.add(new NotesModel(notesTitles.get(i), notesShortcuts.get(i), modifiedTime.get(i), createdTime.get(i)));
        }
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        intent.putExtra("TITLE", notesModels.get(position).getTitle());
        intent.putExtra("SHORTCUT", notesModels.get(position).getNoteShortcut());
        intent.putExtra("MODIFIED", notesModels.get(position).getModifiedTime());
        intent.putExtra("CREATED", notesModels.get(position).getCreatedTime());

        startActivity(intent);

    }

    @SuppressLint("Range")
    public void fetchData() {
        Cursor cursor = dbManager.fetch();

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    notesTitles.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE)));
                    notesShortcuts.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.NOTE_TEXT)));
                    createdTime.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CREATED)));
                    modifiedTime.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.LAST_MODIFIED)));

                } while (cursor.moveToNext());
            }

            cursor.close(); // Close the cursor after use
        } else {
            // Handle the case where the table is empty
        }
    }


}