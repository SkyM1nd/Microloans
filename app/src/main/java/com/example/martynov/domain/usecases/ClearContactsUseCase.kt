package com.example.martynov.domain.usecases

import com.example.martynov.domain.entities.ConfigDataSource
import com.example.martynov.domain.entities.ContactEntity
import com.example.martynov.domain.repository.ContactsRepository

class ClearContactsUseCase(private val contactsRepository: ContactsRepository) {

    suspend fun execute(configDataSource: ConfigDataSource): List<ContactEntity> =
        contactsRepository.clearContacts(configDataSource = configDataSource)
}