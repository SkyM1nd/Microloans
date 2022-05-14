package com.example.martynov.data.storage

import com.example.martynov.domain.entities.ContactEntity

interface ContactsStorage {

    suspend fun addContact(contactEntity: ContactEntity)

    suspend fun getContacts(): List<ContactEntity>

    suspend fun clearContacts()

    suspend fun getCountContacts(): Int

    fun mapToContactEntityForStorage(contactEntity: ContactEntity): DataSourceType

    fun getPreferencesName(): String

    suspend fun editContact(contactEntity: ContactEntity)

    suspend fun deleteContact(contactEntity: ContactEntity)

}