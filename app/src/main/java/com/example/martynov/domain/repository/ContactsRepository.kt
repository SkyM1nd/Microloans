package com.example.martynov.domain.repository

import com.example.martynov.domain.entities.ConfigDataSource
import com.example.martynov.domain.entities.ContactEntity

interface ContactsRepository {

    suspend fun getContacts(configDataSource: ConfigDataSource): List<ContactEntity>

    suspend fun clearContacts(configDataSource: ConfigDataSource): List<ContactEntity>

    suspend fun editContact(
        configDataSource: ConfigDataSource,
        contactEntity: ContactEntity
    ): List<ContactEntity>

    suspend fun addContact(
        configDataSource: ConfigDataSource,
        contactEntity: ContactEntity
    ): List<ContactEntity>

    suspend fun deleteContact(
        configDataSource: ConfigDataSource,
        contactEntity: ContactEntity
    ): List<ContactEntity>

}