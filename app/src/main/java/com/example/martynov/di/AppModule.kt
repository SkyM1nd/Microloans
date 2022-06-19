package com.example.martynov.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var mApplication: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Context {
        return mApplication.applicationContext
    }
}