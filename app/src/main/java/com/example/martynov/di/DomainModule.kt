package com.example.martynov.di

import com.example.martynov.data.repository.LoanRepositoryImpl
import com.example.martynov.data.repository.LoginRepositoryImpl
import com.example.martynov.domain.repository.LoanRepository
import com.example.martynov.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {

    @Singleton
    @Binds
    fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository

    @Singleton
    @Binds
    fun bindLoanRepository(impl: LoanRepositoryImpl): LoanRepository
}