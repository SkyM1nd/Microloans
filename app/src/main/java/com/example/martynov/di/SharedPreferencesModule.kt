package com.example.martynov.di

import android.content.Context
import com.example.martynov.data.storage.sharedpreferences.SharedPreferencesStorage
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {

    @Provides
    fun provideSharedPreferencesStorage(context: Context): SharedPreferencesStorage {
        return SharedPreferencesStorage(context = context)
    }
}