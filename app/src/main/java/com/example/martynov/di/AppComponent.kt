package com.example.martynov.di

import com.example.martynov.presentation.MainActivity
import com.example.martynov.presentation.fragments.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DomainModule::class,
        FileModule::class,
        PhoneBookModule::class,
        RoomModule::class,
        SharedPreferencesModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(databaseFragment: DatabaseFragment)
    fun inject(fileFragment: FileFragment)
    fun inject(addFragment: AddFragment)
    fun inject(editorFragment: EditorFragment)
    fun inject(selectSaveFragment: SelectSaveFragment)
}