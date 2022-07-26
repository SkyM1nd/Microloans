package com.example.martynov.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.domain.usecase.*
import com.github.terrakok.cicerone.Router

class LoginViewModelFactory(
    private val router: Router,
    private val loginUseCase: LoginUseCase,
    private val registrationUseCase: RegistrationUseCase,
    private val reentryUseCase: ReentryUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val isFirstLoginUseCase: IsFirstLoginUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(
            router,
            loginUseCase,
            registrationUseCase,
            reentryUseCase,
            saveTokenUseCase,
            isFirstLoginUseCase
        ) as T
    }
}