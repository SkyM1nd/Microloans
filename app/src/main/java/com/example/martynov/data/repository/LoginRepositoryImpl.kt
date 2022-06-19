package com.example.martynov.data.repository

import com.example.martynov.data.mapper.MapFlowResult
import com.example.martynov.data.mapper.MapToUserDTO
import com.example.martynov.data.storage.loginstorage.localstorage.LoginLocalStorage
import com.example.martynov.data.storage.loginstorage.remotestorage.LoginRemoteStorage
import com.example.martynov.domain.entity.RepositoryResult
import com.example.martynov.domain.entity.UserEntity
import com.example.martynov.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginLocalStorage: LoginLocalStorage,
    private val loginRemoteStorage: LoginRemoteStorage
) : LoginRepository {

    override fun registration(userEntity: UserEntity): Flow<RepositoryResult<String>> {
        val userDTO = MapToUserDTO(userEntity)
        return loginRemoteStorage.registration(userDTO).map { result ->
            MapFlowResult.mapFlowResult(result) { result.data.toString() }
        }
    }

    override fun login(userEntity: UserEntity): Flow<RepositoryResult<String>> {
        val userDTO = MapToUserDTO(userEntity)
        return loginRemoteStorage.signIn(userDTO)
    }

    override fun reentry(): String? = loginLocalStorage.getToken()

    override fun saveToken(token: String) = loginLocalStorage.saveToken(token)

    override fun isFirstLogin(): Boolean = loginLocalStorage.isFirstLogin()
}