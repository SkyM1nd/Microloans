package com.example.martynov.ui.snackbar

import android.view.View
import com.example.martynov.utils.Constants
import com.google.android.material.snackbar.Snackbar

class LoanSnackbar(private val view: View) : AppSnackbar() {

    override fun showSnackbarToCodeResponse(code: String) {
        when (code) {
            else -> showSnackbar(view, Constants.INCORRECT_DATA)
        }
    }

    fun showSnackbarToAmountZero() {
        showSnackbar(view, Constants.AMOUNT_ZERO)
    }

    fun showSnackbarToEmptyFields() {
        showSnackbar(view,  Constants.EMPTY_FIELDS)
    }

    fun showSnackbarToIncorrectData() {
        showSnackbar(view,  Constants.INCORRECT_DATA)
    }

    fun showSnackbarToUnknownError() {
        Snackbar.make(
            view,
            Constants.UNKNOWN_ERROR,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}