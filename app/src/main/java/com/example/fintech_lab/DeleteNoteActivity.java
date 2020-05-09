package com.example.fintech_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DeleteNoteActivity extends AppCompatActivity {

    Spinner notesDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        // Dropdown notes list.
        notesDropdown = (Spinner) findViewById(R.id.notesDropdown);

        // Check if there are some titles already.
        SharedPreferences settings = getApplicationContext().getSharedPreferences("notes", 0);
        String titlesString = settings.getString("titles", "");
        String[] titles = titlesString.split(";");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                titles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notesDropdown.setAdapter(adapter);
    }

    public void onDeleteSelectedNoteButtonClick(View view) {
        String type = notesDropdown.getSelectedItem().toString();

        // Check if there are some titles already.
        SharedPreferences settings = getApplicationContext().getSharedPreferences("notes", 0);
        String titlesString = settings.getString("titles", "");

        // If there are some titles and existing one selected, then delete.
        if (!titlesString.isEmpty() && !type.isEmpty())
        {
            String[] titles = titlesString.split(";");
            String newTitlesString = "";
            for (String title : titles)
            {
                if (!title.equals(type))
                {
                    newTitlesString += title + ";";
                }
            }
            // Remove last ; from titles string.
            newTitlesString = newTitlesString.substring(0, newTitlesString.length() - 1);

            // Write new titles string.
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("titles", newTitlesString);
            editor.apply();
        }

        // Return to main activity screen.
        startActivity(new Intent(DeleteNoteActivity.this, MainActivity.class));
    }
}
