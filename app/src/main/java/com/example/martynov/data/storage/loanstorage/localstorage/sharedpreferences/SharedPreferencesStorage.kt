package com.example.martynov.data.storage.loanstorage.localstorage.sharedpreferences

import android.content.Context
import com.example.martynov.data.storage.loanstorage.localstorage.LoanLocalStorage
import com.example.martynov.utils.Constants
import javax.inject.Inject

class SharedPrefLoanLocalStorage @Inject constructor(private val context: Context) :
    LoanLocalStorage {

    private val mSettings =
        context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun getToken(): String? =
        mSettings.getString(Constants.TOKEN_KEY, null)

    override fun deleteToken() {
        mSettings.edit().putString(Constants.TOKEN_KEY, null).apply()
    }

    override fun setNotFirstLogin() {
        mSettings.edit().putBoolean(Constants.IS_FIRST_LOGIN_KEY, false).apply()
    }
}
