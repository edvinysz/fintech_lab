package com.example.fintech_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView list;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();

        Note[] notes = getNotes();

        // Explode data to separate string arrays.
        String[] noteTitles = new String[notes.length];
        String[] noteTexts = new String[notes.length];
        for (int i = 0; i < notes.length; i++)
        {
            if (notes[i] != null) {
                noteTitles[i] = notes[i].getTitle();
                noteTexts[i] = notes[i].getNoteText();
            }
        }

        // Render custom list view.
        CustomList adapter = new
                CustomList(MainActivity.this, noteTexts, noteTitles);
        list=(ListView)findViewById(R.id.notesList);
        list.setAdapter(adapter);
    }

    public void onAddNoteButtonClick(View view) {
        startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
    }

    public void onDeleteNoteButtonClick(View view) {
        startActivity(new Intent(MainActivity.this, DeleteNoteActivity.class));
    }

    public Note[] getNotes() {
        Note[] notes;

        // Check if there are some titles already.
        SharedPreferences settings = getApplicationContext().getSharedPreferences("notes", 0);
        String titlesString = settings.getString("titles", "");
        // If empty, lets create some.
        if (titlesString.isEmpty())
        {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("Example title 1", "Example text 1");
            editor.putString("Example title 2", "Example text 2");
            editor.putString("titles", "Example title 1;Example title 2");
            // Apply the edits!
            editor.apply();

            notes = new Note[2];
            notes[0] = new Note("test1", "testas1");
            notes[1] = new Note("test2", "testas2");
        }
        else
        {
            String[] titles = titlesString.split(";");
            notes = new Note[titles.length];
            for (int i = 0; i < titles.length; i++)
            {
                String noteText = settings.getString(titles[i], "");
                notes[i] = new Note(titles[i], noteText);
            }
        }

        return notes;
    }

    /*
    Show error message using Toast.
     */
    public void showError(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
