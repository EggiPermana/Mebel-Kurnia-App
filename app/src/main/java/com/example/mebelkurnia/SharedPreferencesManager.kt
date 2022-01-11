package com.example.mebelkurnia

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {
    private var prefs: SharedPreferences = context.applicationContext.getSharedPreferences("mebel_pref", Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    fun setStringPreference(prefKey: String, value:String){
        editor.putString(prefKey, value)
        editor.apply()
    }

    fun clearPreferenceByKey(prefKey: String){
        editor.remove(prefKey)
        editor.apply()
    }

    val getUserId = prefs.getString("userId", "")
    val getUserName = prefs.getString("username", "")
}