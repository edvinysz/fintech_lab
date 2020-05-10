package com.example.fintech_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fintech_lab.utilities.AsyncDataLoader;
import com.example.fintech_lab.utilities.Constants;

public class MainActivity extends AppCompatActivity {
    ListView list;
    public static Context context;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();
        this.tvStatus = findViewById(R.id.tv_status);

        this.tvStatus.setText("Loading data...");
        new AsyncDataLoader() {
            @Override
            public void onPostExecute(String result) {
                // Split to array.
                String[] values = result.split(";");
                String[] currencies = new String[values.length/2];
                String[] rates = new String[values.length/2];

                // separate index for arrays.
                int j = 0;
                for (int i = 0; i < values.length; i += 2)
                {
                    if (values[i] != null) {
                        currencies[j] = values[i];
                        rates[j] = values[i+1];
                        j++;
                    }
                }

                tvStatus.setText("Data loaded:");
                CustomList adapter = new
                        CustomList(MainActivity.this, rates, currencies);
                list=(ListView)findViewById(R.id.notesList);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }.execute(Constants.EURO_RATES_URL);

    }
}
