package com.example.martynov.domain.repository

import com.example.martynov.domain.entity.RepositoryResult
import com.example.martynov.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun registration(userEntity: UserEntity): Flow<RepositoryResult<String>>

    fun login(userEntity: UserEntity): Flow<RepositoryResult<String>>

    fun reentry(): String?

    fun saveToken(token: String)

    fun isFirstLogin(): Boolean
}