package com.youtube.sorcjc.sga_mobile.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;
import com.youtube.sorcjc.sga_mobile.domain.User;

public class Global {
    public static void saveInSharedPreferences(Activity activity, String key, User usuario) {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        editor.putString(key, json);
        editor.apply();
    }

    public static User getFromSharedPreferences(Activity activity,String key)
    {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(key, "");
        User obj = gson.fromJson(json, User.class);
        return obj;
    }

    public static void clearSharedPreferences(Activity activity)
    {
        saveInSharedPreferences(activity,"user_login",null);
    }

    public static void hideKeyBoard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
