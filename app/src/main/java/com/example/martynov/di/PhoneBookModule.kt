package com.example.martynov.di

import android.content.Context
import com.example.martynov.data.storage.phonebook.PhoneBookStorage
import dagger.Module
import dagger.Provides

@Module
class PhoneBookModule {

    @Provides
    fun providePhoneBookStorage(context: Context): PhoneBookStorage {
        return PhoneBookStorage(context = context)
    }
}