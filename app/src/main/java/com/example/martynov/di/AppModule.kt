package com.example.martynov.di

import android.app.Application
import android.content.Context
import com.example.martynov.domain.usecases.*
import com.example.martynov.presentation.ContactsViewModel
import com.example.martynov.presentation.ContactsViewModelFactory
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

    @Provides
    @Singleton
    fun provideContactsViewModelFactory(
        getContactsUseCase: GetContactsUseCase,
        clearContactsUseCase: ClearContactsUseCase,
        editContactUseCase: EditContactUseCase,
        addContactUseCase: AddContactUseCase,
        deleteContactUseCase: DeleteContactUseCase
    ): ContactsViewModelFactory {
        return ContactsViewModelFactory(
            getContactsUseCase = getContactsUseCase,
            clearContactsUseCase = clearContactsUseCase,
            editContactUseCase = editContactUseCase,
            addContactUseCase = addContactUseCase,
            deleteContactUseCase = deleteContactUseCase
        )
    }

}