package com.example.martynov.app

import android.app.Application
import com.example.martynov.di.AppComponent
import com.example.martynov.di.AppModule
import com.example.martynov.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}