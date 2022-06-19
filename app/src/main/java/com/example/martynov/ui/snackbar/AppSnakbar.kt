package com.example.martynov.ui.snackbar

import android.view.View
import com.google.android.material.snackbar.Snackbar

abstract class AppSnackbar {

    abstract fun showSnackbarToCodeResponse(code: String)

    fun showSnackbar(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
    }
}