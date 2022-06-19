package com.example.martynov.domain.usecase

import com.example.martynov.domain.repository.LoginRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    operator fun invoke(token: String) {
        loginRepository.saveToken(token)
    }
}