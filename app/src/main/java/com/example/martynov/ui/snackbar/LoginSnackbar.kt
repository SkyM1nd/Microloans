package com.example.martynov.ui.snackbar

import android.view.View
import com.example.martynov.utils.Constants
import com.google.android.material.snackbar.Snackbar

//TODO Вынести
class LoginSnackbar(private val view: View) : AppSnackbar() {

    override fun showSnackbarToCodeResponse(code: String) {
        when (code) {
            "404" -> showSnackbar(view, Constants.USER_NOT_FOUND)
            "400" -> showSnackbar(view, Constants.USERNAME_TAKEN)
            else -> showSnackbarToUnknownError()
        }
    }

    fun showSnackbarToEmptyFields() {
        showSnackbar(view, Constants.EMPTY_FIELDS)
    }

    fun showSnackbarSuccessRegistration() {
        showSnackbar(view, Constants.USER_CREATE)
    }

    fun showSnackbarToUnknownError() {
        Snackbar.make(view, Constants.UNKNOWN_ERROR, Snackbar.LENGTH_SHORT).show()
    }
}