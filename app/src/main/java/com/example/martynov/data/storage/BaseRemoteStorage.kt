package com.example.martynov.data.storage

import com.example.martynov.domain.entity.RepositoryResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call

abstract class BaseRemoteStorage {

    fun <T> doRequest(call: Call<T>): Flow<RepositoryResult<T>> = flow {
        emit(RepositoryResult.Loading(isLoading = true))

        val result = call.execute()

        if (result.isSuccessful) {
            result.body()?.let {
                emit(RepositoryResult.Success(it))
            }
        } else {
            emit(RepositoryResult.Error(result.code().toString()))
        }
    }
}