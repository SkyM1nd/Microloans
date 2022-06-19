package com.example.martynov.data.storage.loginstorage.remotestorage.retrofit

import com.example.martynov.data.model.UserDTO
import com.example.martynov.data.storage.BaseRemoteStorage
import com.example.martynov.data.storage.loginstorage.remotestorage.LoginRemoteStorage
import com.example.martynov.domain.entity.RepositoryResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.Retrofit
import javax.inject.Inject

//TODO Дописать случаи всех кодов

class LoginApi @Inject constructor(private val retrofit: Retrofit) : LoginRemoteStorage,
    BaseRemoteStorage() {

    private val api: LoginService = retrofit.create(LoginService::class.java)

    override fun signIn(userDTO: UserDTO): Flow<RepositoryResult<String>> {
        val call = api.login(userDTO)

        return flow {
            emit(RepositoryResult.Loading(isLoading = true))

            val result = call.execute()

            if (result.isSuccessful) {
                emit(RepositoryResult.Success(result.body()?.string()))
            } else {
                emit(RepositoryResult.Error(result.code().toString()))
            }
        }
    }

    override fun registration(userDTO: UserDTO): Flow<RepositoryResult<ResponseBody>> {
        val call = api.registration(userDTO)

        return doRequest(call)
    }
}