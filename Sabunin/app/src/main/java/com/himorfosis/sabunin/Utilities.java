package com.himorfosis.sabunin;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by him on 3/28/2018.
 */

public class Utilities {

    public static void putPrefName(String DBNAME, String Tablekey, String value, Context context) {

        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(DBNAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(Tablekey, value);
        editor.commit();
    }

    public static String getValueName ( String DBNAME, String Tablekey, Context context) {

        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(DBNAME, Context.MODE_PRIVATE);
        text = settings.getString(Tablekey, null);
        return text;
    }

    public static void clearSharedPreferance( String DBNAME, Context context) {

        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences( DBNAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.commit();
    }
}
