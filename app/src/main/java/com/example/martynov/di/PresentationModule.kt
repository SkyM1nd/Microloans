package com.example.martynov.di

import com.example.martynov.domain.usecase.*
import com.example.martynov.presentation.viewmodel.LoanViewModelFactory
import com.example.martynov.presentation.viewmodel.LoginViewModelFactory
import com.example.martynov.presentation.viewmodel.NewLoanViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentationModule {

    @Provides
    @Singleton
    fun provideLoginViewModelFactory(
        loginUseCase: LoginUseCase,
        registrationUseCase: RegistrationUseCase,
        reentryUseCase: ReentryUseCase,
        saveTokenUseCase: SaveTokenUseCase,
        isFirstLoginUseCase: IsFirstLoginUseCase
    ): LoginViewModelFactory {
        return LoginViewModelFactory(
            loginUseCase,
            registrationUseCase,
            reentryUseCase,
            saveTokenUseCase,
            isFirstLoginUseCase
        )
    }

    @Provides
    @Singleton
    fun provideLoanViewModelFactory(
        getLoanHistoryUseCase: GetLoanHistoryUseCase,
        getLoanDetailUseCase: GetLoanDetailUseCase,
        deleteTokenUseCase: DeleteTokenUseCase,
        setNotFirstLoginUseCase: SetNotFirstLoginUseCase
    ): LoanViewModelFactory {
        return LoanViewModelFactory(
            getLoanHistoryUseCase,
            getLoanDetailUseCase,
            deleteTokenUseCase,
            setNotFirstLoginUseCase
        )
    }

    @Provides
    @Singleton
    fun provideNewLoanViewModelFactory(
        getLoanConditionsUseCase: GetLoanConditionsUseCase,
        createNewLoanUseCase: CreateNewLoanUseCase
    ): NewLoanViewModelFactory {
        return NewLoanViewModelFactory(
            getLoanConditionsUseCase,
            createNewLoanUseCase
        )
    }
}