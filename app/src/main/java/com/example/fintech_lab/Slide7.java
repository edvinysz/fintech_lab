package com.example.fintech_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class Slide7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_slide7);

        InputStream stream = null;
        try {
            stream = getAssets().open("logo3.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GifWebView view = new GifWebView(this, "file:///android_asset/logo3.gif");

        String[] textArray = {"Ačiū už dėmesį!"};
        LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(this);
        textView.setText(textArray[0]);
        textView.setTextSize(32);
        textView.setPadding(250, 100, 0, 500);
        linearLayout.addView(textView);
        linearLayout.addView(view);
    }
}
