package com.example.fintech_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner dropdown_menu;
    public Context context = getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dropdown menu for word counter type.
        dropdown_menu = (Spinner) findViewById(R.id.countWordsType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.count_words_types));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_menu.setAdapter(adapter);
    }

    public void onCountButtonClick(View view) {
        String type = dropdown_menu.getSelectedItem().toString();

        EditText word_count_input = (EditText) findViewById(R.id.countWordsInput);
        String input = word_count_input.getText().toString();

        WordCountHelper word_count_helper = new WordCountHelper();
        int result = word_count_helper.wordCounterByType(type, input);

        TextView count_words_result = findViewById(R.id.countWordsResults);
        count_words_result.setText(Integer.toString(result));
    }
}
