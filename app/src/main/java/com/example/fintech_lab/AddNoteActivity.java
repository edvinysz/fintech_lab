package com.example.fintech_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AddNoteActivity extends AppCompatActivity {

    Note note;
    Context context;
    ObjectOutputStream os;
    MainActivity main = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    public void onSaveNoteButtonClick(View view) {
        // Get text from text boxes.
        EditText titleBox = (EditText) findViewById(R.id.title);
        String title = titleBox.getText().toString();

        EditText noteTextBox = (EditText) findViewById(R.id.noteText);
        String noteText = noteTextBox.getText().toString();

        // Fields validation.
        if (noteText.isEmpty() || title.isEmpty())
        {
            main.showError("Empty boxes not allowed!");
            // Return to main activity screen.
            startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
        }
        if (title.indexOf(";") != -1)
        {
            main.showError("Character ; is not allowed in title because of this weird data structure.");
            // Return to main activity screen.
            startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
        }

        // Get existing titles string.
        SharedPreferences settings = getApplicationContext().getSharedPreferences("notes", 0);
        SharedPreferences.Editor editor = settings.edit();
        String titlesString = settings.getString("titles", "");
        // Create new titles string and write.
        String newTitlesString = titlesString + ";" + title;
        editor.putString("titles", newTitlesString);
        editor.putString(title, noteText);
        // Apply the edits.
        editor.apply();

        // Return to main activity screen.
        startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
    }
}
