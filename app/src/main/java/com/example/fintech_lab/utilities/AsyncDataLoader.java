package com.example.fintech_lab.utilities;

import android.os.AsyncTask;

import com.example.fintech_lab.Entry;
import com.example.fintech_lab.parsers.EuroRatesParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.example.fintech_lab.utilities.Constants.EURO_RATES_URL;

public class AsyncDataLoader extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        try {
            return loadXmlFromNetwork(EURO_RATES_URL);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    private String loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        // Instantiate the parser
        EuroRatesParser euroRatesParser = new EuroRatesParser();
        List<Entry> entries = null;

        StringBuilder entriesString = new StringBuilder();

        try {
            stream = downloadUrlContent(urlString);
            entries = euroRatesParser.getCurrencyRates(stream);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        // Make a single string with all values.
        for (Entry entry : entries) {
            entriesString.append(entry.getCurrency());
            entriesString.append(";");
            entriesString.append(entry.getRate());
            entriesString.append(";");
        }

        // Remove last ;.
        entriesString.deleteCharAt(entriesString.length() - 1);

        return entriesString.toString();
    }

    //Routine that creates and calls GET request to web page
    private static InputStream downloadUrlContent(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
}
