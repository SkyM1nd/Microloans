package com.example.martynov.domain.usecase

import com.example.martynov.domain.repository.LoginRepository
import javax.inject.Inject

class IsFirstLoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    operator fun invoke(): Boolean =
        loginRepository.isFirstLogin()
}