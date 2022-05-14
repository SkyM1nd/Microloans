package com.example.martynov.data.storage.sharedpreferences

import android.content.Context

class SharedPreferencesStorage(private val context: Context) {

    private val mSettings = context.getSharedPreferences("Flag", Context.MODE_PRIVATE)

    fun addFlag(name: String) {
        mSettings.edit().putBoolean(name, true).apply()

    }

    fun getStateFlag(name: String): Boolean =
        mSettings.getBoolean(name, false)

}