package com.gamezzar.geargymtest.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.gamezzar.geargymtest.domain.UserModel;

public class SessionManager {
    private static final String PREF_NAME = "userSession";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_EMAIL = "email";

    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;
    private final Context _context;

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    // Create login session
    public void createLoginSession(int userId, String email) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(KEY_USER_ID, userId);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    // Check login status
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    // Get stored session data
    public UserModel getUserDetails() {
        if (!isLoggedIn()) {
            return null;
        }
        int userId = pref.getInt(KEY_USER_ID, -1);
        String email = pref.getString(KEY_EMAIL, null);
        return new UserModel(userId, email);
    }

    // Logout user
    public void logoutUser() {
        editor.clear();
        editor.commit();
    }
}