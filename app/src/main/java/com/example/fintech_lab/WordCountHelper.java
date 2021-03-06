package com.example.fintech_lab;

import android.content.Context;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

public class WordCountHelper {

    /*
    Main word calculation function.

    @return int
        Return word count by selected type.
     */
    public int wordCounterByType(String type, String input, Context context) {
        if (!validate(type, input, context)) {
            return 0;
        }

        int result;

        if (type.equals("Words")) {
            result = countWordsBySpaces(input);
        }
        else {
            result = countSymbols(input);
        }

        return result;
    }

    /*
    @return boolean
        If validation passed, return true, else false.
     */
    private boolean validate(String type, String input, Context context) {
        if (input.isEmpty()) {
            String error_message = context.getResources().getString(R.string.error_input_empty);
            showError(context,error_message);
            return false;
        }
        else if (type.isEmpty()) {
            String error_message = context.getResources().getString(R.string.error_type_empty);
            showError(context,error_message);
            return false;
        }

        return true;
    }

    /*
    Show error message using Toast.
     */
    private void showError(Context context, String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    /*
    @return int result
        Calculate number of words divided by spaces.
     */
    private int countWordsBySpaces(String input) {
        String[] words = input.split("\\s+");
        return words.length;
    }

    /*
    @return int result
        Calculate number of words divided by symbols.
     */
    private int countSymbols(String input) {
        int result = 0;
        List<Character> punctuations = Arrays.asList('.', ',', ':', ';', '-', '_', '?', '!', '(', ')', '"');

        for(int i = 0; i < input.length(); i++) {
            if (punctuations.contains(input.charAt(i))) {
                result++;
            }
        }

        return result;
    }
}
