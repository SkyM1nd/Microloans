package com.example.martynov.di

import com.example.martynov.data.repository.ContactsRepositoryImpl
import com.example.martynov.data.storage.database.DatabaseContactsStorage
import com.example.martynov.data.storage.file.FileContactsStorage
import com.example.martynov.data.storage.phonebook.PhoneBookStorage
import com.example.martynov.data.storage.sharedpreferences.SharedPreferencesStorage
import com.example.martynov.domain.repository.ContactsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideContactsRepository(
        databaseContactsStorage: DatabaseContactsStorage,
        fileContactsStorage: FileContactsStorage,
        phoneBookStorage: PhoneBookStorage,
        sharedPreferencesStorage: SharedPreferencesStorage
    ): ContactsRepository {
        return ContactsRepositoryImpl(
            databaseContactsStorage = databaseContactsStorage,
            fileContactsStorage = fileContactsStorage,
            phoneBookStorage = phoneBookStorage,
            sharedPreferencesStorage = sharedPreferencesStorage
        )
    }

}