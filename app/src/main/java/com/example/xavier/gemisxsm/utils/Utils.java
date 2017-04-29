package com.example.xavier.gemisxsm.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Xavier on 23/04/2017.
 */

public class Utils {

    /**
     * Hides soft keyboard
     * @param activity context for getting systemService
     */
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    /**
     * Hides spinner of profile picture after loading
     * @param spinner Target spinner
     * @return The listener
     */
    //FUNCION PARA OCULTAR LOADING DE PROFILE PICTURE AL TERMINAR LOADING
 /*   public static SimpleImageLoadingListener loadingFinished(ProgressBar spinner){
        final ProgressBar spin = spinner;
        return new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                spin.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                spin.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                spin.setVisibility(View.GONE);
            }
        };
    }
*/
    /**
     * <p>Capitalizes a String changing the first letter to title case as
     * per {@link Character#toTitleCase(char)}. No other letters are changed.</p>
     * A {@code null} input String returns {@code null}.</p>
     * <pre>
     * StringUtils.capitalize(null)  = null
     * StringUtils.capitalize("")    = ""
     * StringUtils.capitalize("cat") = "Cat"
     * StringUtils.capitalize("cAt") = "CAt"
     * </pre>
     *
     * @param word the String to capitalize, may be null
     * @return the capitalized String, {@code null} if null String input
     */
    public static String capitalize(final String word) {
        int strLen;
        if (word == null || (strLen = word.length()) == 0) {
            return word;
        }
        final char firstChar = word.charAt(0);
        if (Character.isTitleCase(firstChar)) {
            // already capitalized
            return word;
        }
        return new StringBuilder(strLen)
                .append(Character.toTitleCase(firstChar))
                .append(word.substring(1))
                .toString();
    }
/*
    public static Spanned formatDisplayableText(String text) {
        return Html.fromHtml(replaceEnters(replaceEmojis(text)));
    }
*/
    private static String replaceEnters(String text) {
        return text.replace("\n", "<br>");
    }
/*
    private static String replaceEmojis(String text) {
        return EmojiParser.parseToUnicode(text);
    }
*/
    public static int darker (int color, float factor) {
        int a = Color.alpha( color );
        int r = Color.red( color );
        int g = Color.green( color );
        int b = Color.blue( color );

        return Color.argb( a,
                Math.max( (int)(r * factor), 0 ),
                Math.max( (int)(g * factor), 0 ),
                Math.max( (int)(b * factor), 0 ) );
    }

    public static String loadJSONFromAsset(Context context, String filename) {
        String json;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
