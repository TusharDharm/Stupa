package com.example.stupa.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager(internal var context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("UserFile", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()

    var email: String
        get() = preferences.getString(Constants.EMAIL, "").toString()
        set(value) {
            editor.putString(Constants.EMAIL, value)
            editor.commit()
        }



    fun clearData() {
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }


}