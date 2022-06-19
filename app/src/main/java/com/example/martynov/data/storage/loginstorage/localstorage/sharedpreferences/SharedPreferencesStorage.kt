package com.example.martynov.data.storage.loginstorage.localstorage.sharedpreferences

import android.content.Context
import com.example.martynov.data.storage.loginstorage.localstorage.LoginLocalStorage
import com.example.martynov.utils.Constants
import javax.inject.Inject

class SharedPrefLoginLocalStorage @Inject constructor(private val context: Context) :
    LoginLocalStorage {

    private val mSettings =
        context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun saveToken(token: String) {
        mSettings.edit().putString(Constants.TOKEN_KEY, token).apply()
    }

    override fun getToken(): String? =
        mSettings.getString(Constants.TOKEN_KEY, null)

    override fun isFirstLogin(): Boolean =
        mSettings.getBoolean(Constants.IS_FIRST_LOGIN_KEY, true)
}
