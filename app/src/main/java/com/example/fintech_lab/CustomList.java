package com.example.fintech_lab;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] noteTitles;
    private final String[] noteTexts;
    public CustomList(Activity context,
                      String[] noteTitles, String[] noteTexts) {
        super(context, R.layout.list_single, noteTitles);
        this.context = context;
        this.noteTitles = noteTitles;
        this.noteTexts = noteTexts;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.noteBodyText);
        TextView imageView = (TextView) rowView.findViewById(R.id.noteTitleText);
        txtTitle.setText(noteTitles[position]);
        imageView.setText(noteTexts[position]);

        return rowView;
    }
}