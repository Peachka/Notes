package com.example.notes;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String title = getIntent().getStringExtra("TITLE");
        String text = getIntent().getStringExtra("SHORTCUT");
        String modified = getIntent().getStringExtra("MODIFIED");
        String created = getIntent().getStringExtra("CREATED");
        EditText titleText = findViewById(R.id.titleText);
        EditText noteText = findViewById(R.id.noteText);
        TextView modifiedTime = findViewById(R.id.modifiedText);
        TextView createdTime = findViewById(R.id.createdText);
        ImageView backTab = findViewById(R.id.backTab);
        ImageView deleteNote = findViewById(R.id.delete);



        // Calculate the available screen height in pixels
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;

        // Calculate a reasonable maxLines value based on the screen height
        int lineHeight = noteText.getLineHeight(); // Get the height of one line
        int maxLines = screenHeight / lineHeight - 6; // Calculate maxLines based on screen height

        // Set the calculated maxLines value to the EditText
        noteText.setMaxLines(maxLines);
        titleText.setText(title);
        noteText.setText(text);
        modifiedTime.setText("Modified: "+ modified);
        createdTime.setText("Created: " + created);

        dbManager = new DatabaseManager(this);

        try {
            dbManager.open();
        }catch (Exception e){
            e.printStackTrace();
        }

        backTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeInDB = createdTime.getText().toString();
                String timeInDBSUb = timeInDB.substring(timeInDB.indexOf(":") + 2);

                if(dbManager.noteAlreadyExist(timeInDBSUb)){
                    dbManager.update(titleText.getText().toString(), getTime(), timeInDBSUb , noteText.getText().toString());
                }
                else{
                    dbManager.insert(titleText.getText().toString(), getTime(), timeInDBSUb, noteText.getText().toString());
                }

                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeInDB = createdTime.getText().toString();
                dbManager.delete(timeInDB.substring(timeInDB.indexOf(":") + 2));
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public static String getTime(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Months are 0-based, so add 1
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24-hour format
        int minute = calendar.get(Calendar.MINUTE);

        String currentDateTime = String.format("%04d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
        return currentDateTime;
    }
}
