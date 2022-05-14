package com.example.martynov.domain.usecases

import com.example.martynov.domain.entities.ConfigDataSource
import com.example.martynov.domain.entities.ContactEntity
import com.example.martynov.domain.repository.ContactsRepository

class EditContactUseCase(private val contactsRepository: ContactsRepository) {

    suspend fun execute(
        configDataSource: ConfigDataSource,
        contactEntity: ContactEntity
    ): List<ContactEntity> =
        contactsRepository.editContact(
            configDataSource = configDataSource,
            contactEntity = contactEntity
        )

}