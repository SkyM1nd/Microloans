package com.example.martynov.data.storage.loginstorage.remotestorage

import com.example.martynov.data.model.UserDTO
import com.example.martynov.domain.entity.RepositoryResult
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface LoginRemoteStorage {

    fun signIn(userDTO: UserDTO): Flow<RepositoryResult<String>>

    fun registration(userDTO: UserDTO): Flow<RepositoryResult<ResponseBody>>
}