package com.example.martynov.di

import android.content.Context
import com.example.martynov.data.storage.file.FileContactsStorage
import dagger.Module
import dagger.Provides

@Module
class FileModule {

    @Provides
    fun provideFileContactsStorage(context: Context): FileContactsStorage {
        return FileContactsStorage(context = context)
    }
}