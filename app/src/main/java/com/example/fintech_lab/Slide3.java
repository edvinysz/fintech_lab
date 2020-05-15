package com.example.fintech_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Slide3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide4);
    }

    public void onClick(View view) {
        // Return to main activity screen.
        startActivity(new Intent(Slide3.this, Slide5.class));
    }
}
