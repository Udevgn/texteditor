package com.example.texteditor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    private static final String USER_TEXT = "USER_TEXT";
    private static TextEditorApplication textEditorApplication;


    public static void setApplication(TextEditorApplication mTextEditorApplication){
       textEditorApplication = mTextEditorApplication;
    }
    public static void saveText(String text){
        SharedPreferences.Editor prefsEditor = textEditorApplication.getPreferences().edit();
        prefsEditor.putString(USER_TEXT, text);
        prefsEditor.commit();
    }

    public static String getSavedText(){
        return textEditorApplication.getPreferences().getString(USER_TEXT,"");
    }


}
