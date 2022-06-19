package com.example.martynov.di

import com.example.martynov.data.storage.loanstorage.localstorage.LoanLocalStorage
import com.example.martynov.data.storage.loanstorage.localstorage.sharedpreferences.SharedPrefLoanLocalStorage
import com.example.martynov.data.storage.loanstorage.remotestorage.LoanRemoteStorage
import com.example.martynov.data.storage.loanstorage.remotestorage.retrofit.LoanApi
import com.example.martynov.data.storage.loginstorage.localstorage.LoginLocalStorage
import com.example.martynov.data.storage.loginstorage.localstorage.sharedpreferences.SharedPrefLoginLocalStorage
import com.example.martynov.data.storage.loginstorage.remotestorage.LoginRemoteStorage
import com.example.martynov.data.storage.loginstorage.remotestorage.retrofit.LoginApi
import com.example.martynov.utils.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
interface DataModule {

    companion object {

        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Singleton
    @Binds
    fun bindLoginLocalStorage(impl: SharedPrefLoginLocalStorage): LoginLocalStorage

    @Singleton
    @Binds
    fun bindLoanLocalStorage(impl: SharedPrefLoanLocalStorage): LoanLocalStorage

    @Singleton
    @Binds
    fun bindLoginRemoteStorage(impl: LoginApi): LoginRemoteStorage

    @Singleton
    @Binds
    fun bindLoanRemoteStorage(impl: LoanApi): LoanRemoteStorage
}