package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{
    ArrayList<NotesModel> notesModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycrerView);

        setNotesModels();

        NotesRecyclerViewAdapter adapter = new NotesRecyclerViewAdapter(this, notesModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setNotesModels(){
        String[] notesTitles = new String[]{"Title1", "Title2"};
        String[] notesShortcuts = new String[]{"My little note 1", "My little note 2"};

        for(int i = 0; i < notesShortcuts.length; i++){
            notesModels.add(new NotesModel(notesTitles[i], notesShortcuts[i]));
        }
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        intent.putExtra("TITLE", notesModels.get(position).getTitle());
        intent.putExtra("SHORTCUT", notesModels.get(position).getNoteShortcut());

        startActivity(intent);

    }
}