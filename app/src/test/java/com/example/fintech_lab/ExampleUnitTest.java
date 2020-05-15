package com.example.fintech_lab;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private WordCountHelper wordCountHelper;

    @Before
    public void PrepareVariables() {
        wordCountHelper = new WordCountHelper();
    }

    @Test
    public void ValidationTest() {
        assertEquals(0, wordCountHelper.wordCounterByType("", "pirmas antras trecias ketvirtas penktas"));
        assertEquals(0, wordCountHelper.wordCounterByType("Words", ""));
        assertEquals(0, wordCountHelper.wordCounterByType("", ""));
        assertEquals(0, wordCountHelper.wordCounterByType(null, null));
    }

    @Test
    public void CountWordsTest() {
        assertEquals(5, wordCountHelper.wordCounterByType("Words", "pirmas antras trecias ketvirtas penktas"));
        assertEquals(2, wordCountHelper.wordCounterByType("Words", "pirmas.antras%^$trecias ketvirtas[]..penktas"));
        assertEquals(1, wordCountHelper.wordCounterByType("Words", "pirmasantrastreciasketvirtaspenktas"));
        assertEquals(10, wordCountHelper.wordCounterByType("Words", "1 2 3 4 5 6 7 8 9 10"));
        assertEquals(0, wordCountHelper.wordCounterByType("Words", "          "));
        assertEquals(0, wordCountHelper.wordCounterByType("Words", "...................."));
    }

    @Test
    public void CountSymbolsTest() {
        assertEquals(0, wordCountHelper.wordCounterByType("Symbols", "pirmas antras trecias ketvirtas penktas"));
        assertEquals(3, wordCountHelper.wordCounterByType("Symbols", "pirmas.antrastrecias ketvirtas..penktas"));
        assertEquals(0, wordCountHelper.wordCounterByType("Symbols", "pirmasantrastreciasketvirtaspenktas"));
        assertEquals(21, wordCountHelper.wordCounterByType("Symbols", "'.', ',', ':', ';', '-', '_', '?', '!', '(', ')', '\"'"));
        assertEquals(0, wordCountHelper.wordCounterByType("Symbols", "          "));
        assertEquals(20, wordCountHelper.wordCounterByType("Symbols", "...................."));
    }
}