package com.example.fintech_lab.parsers;

import android.util.Xml;

import com.example.fintech_lab.Entry;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EuroRatesParser {

    private static final String ns = null;

    public static List getCurrencyRates(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);

            // Long walk to the required tag.
            parser.nextTag();
            parser.next();
            parser.nextTag();
            parser.next();
            parser.nextTag();
            parser.next();
            parser.next();
            parser.nextTag();
            parser.next();
            parser.nextTag();
            parser.next();
            parser.nextTag();
            parser.next();
            parser.nextTag();
            parser.next();
            parser.nextTag();
            parser.next();
            parser.nextTag();

            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private static List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        for (int i = 0; i < 32; i++)
        {
            entries.add(readEntry(parser));
            parser.next();
            parser.nextTag();
        }

        return entries;
    }

    private static Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        String currency = parser.getAttributeValue(0);
        String rate = parser.getAttributeValue(1);
        return new Entry(currency, rate);
    }
}
