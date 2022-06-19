package com.example.martynov.presentation

import android.content.Context
import android.content.Intent
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.martynov.domain.entity.RepositoryResult
import com.example.martynov.domain.entity.ResultStatus
import com.example.martynov.presentation.viewmodel.BaseViewModel
import com.example.martynov.ui.LoginActivity
import com.example.martynov.ui.snackbar.AppSnackbar

abstract class HandlerResult(
    private val context: Context,
    private val viewModel: BaseViewModel,
    private val snackbar: AppSnackbar,
) {

    operator fun <T> invoke(result: RepositoryResult<T>) {
        when (result.status) {
            ResultStatus.SUCCESS -> resultSuccess()
            ResultStatus.ERROR -> resultError(result)
            ResultStatus.LOADING -> resultLoading()
        }
    }

    abstract fun resultSuccess()

    private fun <T> resultError(result: RepositoryResult<T>) {
        viewModel.setProgressBarVisible(false)

        if (result.message == "403") {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }

        snackbar.showSnackbarToCodeResponse(
            result.message.toString()
        )
    }

    private fun resultLoading() {
        viewModel.setProgressBarVisible(true)
    }
}