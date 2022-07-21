package com.example.martynov.domain.entity

enum class ResultStatus {
    SUCCESS,
    ERROR,
    LOADING
}

sealed class RepositoryResult<out T>(val status: ResultStatus, val data: T?, val message: String?) {

    data class Success<out R>(val _data: R?) : RepositoryResult<R>(
        status = ResultStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String) : RepositoryResult<Nothing>(
        status = ResultStatus.ERROR,
        data = null,
        message = exception
    )

    data class Loading<out R>(val isLoading: Boolean) : RepositoryResult<R>(
        status = ResultStatus.LOADING,
        data = null,
        message = null
    )
}