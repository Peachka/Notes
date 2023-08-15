package com.example.notes;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String title = getIntent().getStringExtra("TITLE");
        String text = getIntent().getStringExtra("SHORTCUT");
        TextView textView = findViewById(R.id.textView);
        EditText noteText = findViewById(R.id.noteText);
        // Calculate the available screen height in pixels
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;

        // Calculate a reasonable maxLines value based on the screen height
        int lineHeight = noteText.getLineHeight(); // Get the height of one line
        int maxLines = screenHeight / lineHeight - 4; // Calculate maxLines based on screen height

        // Set the calculated maxLines value to the EditText
        noteText.setMaxLines(maxLines);
        textView.setText(title);
        noteText.setText(text);



    }

}
