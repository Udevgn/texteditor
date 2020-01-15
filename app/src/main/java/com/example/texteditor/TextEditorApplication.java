package com.example.texteditor;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class TextEditorApplication extends Application {
    private SharedPreferences mPreferences;
    private static final String APP_SHARED_PREFS = "APP_PREF" ;
    public SharedPreferences getPreferences() {
        if (mPreferences == null) {
            mPreferences = getSharedPreferences(APP_SHARED_PREFS,
                    Context.MODE_PRIVATE);
        }
        return mPreferences;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Preferences.setApplication(this);
    }
}
