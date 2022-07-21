package com.example.martynov.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel : ViewModel() {

    private val _progressBarVisible = SingleLiveEvent(false)
    val progressBarVisible: LiveData<Boolean> = _progressBarVisible

    private val _error = SingleLiveEvent<String>()
    val error: LiveData<String> = _error

    val handler =
        CoroutineExceptionHandler { _, throwable -> handleError(throwable) }

    fun setProgressBarVisible(value: Boolean) {
        _progressBarVisible.value = value
    }

    private fun handleError(error: Throwable) {
        _error.postValue(error.toString())
    }
}
