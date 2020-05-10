package com.example.fintech_lab;

import android.app.Activity;

import com.example.fintech_lab.utilities.AsyncDataLoader;

import static com.example.fintech_lab.utilities.Constants.EURO_RATES_URL;

public class NetworkActivity extends Activity {
    public static final String WIFI = "Wi-Fi";
    public static final String ANY = "Any";

    // Whether there is a Wi-Fi connection.
    private static boolean wifiConnected = false;
    // Whether there is a mobile connection.
    private static boolean mobileConnected = false;
    // Whether the display should be refreshed.
    public static boolean refreshDisplay = true;
    public static String sPref = null;

    // Uses AsyncTask to download the XML feed from stackoverflow.com.
    public void loadPage() {

        if ((sPref.equals(ANY)) && (wifiConnected || mobileConnected)) {
            new AsyncDataLoader().execute(EURO_RATES_URL);
        } else if ((sPref.equals(WIFI)) && (wifiConnected)) {
            new AsyncDataLoader().execute(EURO_RATES_URL);
        } else {
            // show error
        }
    }
}
