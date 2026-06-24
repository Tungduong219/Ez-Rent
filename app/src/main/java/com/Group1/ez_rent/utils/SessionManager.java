package com.Group1.ez_rent.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "EzRentSession";
    private static final String KEY_PHONE = "user_phone";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createLoginSession(String phone) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_PHONE, phone);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public String getUserPhone() {
        return pref.getString(KEY_PHONE, null);
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }
}
