package com.example.jamil.healthcareapp.Login;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preference {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    static final String NAME = "tyro";
    static final String NAME_KEY = "name";
    static final String PASS_KEY = "pass";
    static final String LOGIN_KEY = "login";

    public Preference(Context context) {
        this.sharedPreferences = context.getSharedPreferences(NAME, 0);
        this.editor = sharedPreferences.edit();
    }

    public void save(String name, String pass) {

        editor.putBoolean(LOGIN_KEY, true);

        editor.putString(NAME_KEY, name);
        editor.putString(PASS_KEY, pass);
        editor.commit();

    }

    public HashMap<String, String> retrieveData() {

        String name = sharedPreferences.getString(NAME_KEY, null);
        String pass = sharedPreferences.getString(PASS_KEY, null);

        HashMap<String, String> map = new HashMap<>();
        map.put(NAME_KEY, name);
        map.put(PASS_KEY, pass);
        return map;
    }

    public boolean isLogin() {

        return sharedPreferences.getBoolean(LOGIN_KEY, false);
    }
}