package com.example.martynov.domain.usecase

import com.example.martynov.domain.entity.RepositoryResult
import com.example.martynov.domain.entity.UserEntity
import com.example.martynov.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    operator fun invoke(userEntity: UserEntity): Flow<RepositoryResult<String>> =
        loginRepository.login(userEntity = userEntity)
}