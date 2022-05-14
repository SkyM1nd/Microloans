package com.example.martynov.di

import com.example.martynov.domain.repository.ContactsRepository
import com.example.martynov.domain.usecases.*
import dagger.Module
import dagger.Provides


@Module
class DomainModule {

    @Provides
    fun provideGetContactsUseCase(contactsRepository: ContactsRepository): GetContactsUseCase {
        return GetContactsUseCase(contactsRepository)
    }

    @Provides
    fun provideClearContactsUseCase(contactsRepository: ContactsRepository): ClearContactsUseCase {
        return ClearContactsUseCase(contactsRepository)
    }

    @Provides
    fun provideEditContactUseCase(contactsRepository: ContactsRepository): EditContactUseCase {
        return EditContactUseCase(contactsRepository)
    }

    @Provides
    fun provideAddContactUseCase(contactsRepository: ContactsRepository): AddContactUseCase {
        return AddContactUseCase(contactsRepository)
    }

    @Provides
    fun provideDeleteContactUseCase(contactsRepository: ContactsRepository): DeleteContactUseCase {
        return DeleteContactUseCase(contactsRepository)
    }

}