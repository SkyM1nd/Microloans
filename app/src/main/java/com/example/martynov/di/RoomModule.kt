package com.example.martynov.di

import android.content.Context
import androidx.room.Room
import com.example.martynov.data.storage.database.AppDataBase
import com.example.martynov.data.storage.database.ContactDAO
import com.example.martynov.data.storage.database.DatabaseContactsStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, "app-db").build()

    @Singleton
    @Provides
    fun provideContactDao(demoDatabase: AppDataBase): ContactDAO {
        return demoDatabase.contactDAO
    }

    @Singleton
    @Provides
    fun provideDatabaseContactsStorage(contactDAO: ContactDAO): DatabaseContactsStorage {
        return DatabaseContactsStorage(contactDAO)
    }

}