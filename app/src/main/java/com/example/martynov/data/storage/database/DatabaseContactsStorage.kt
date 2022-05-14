package com.example.martynov.data.storage.database

import com.example.martynov.data.storage.ContactsStorage
import com.example.martynov.domain.entities.ContactEntity

class DatabaseContactsStorage(private val contactDAO: ContactDAO) : ContactsStorage {

    private val APP_PREFERENCES_NAME = "contacts_database"

    override fun getPreferencesName(): String = APP_PREFERENCES_NAME

    override suspend fun editContact(contactEntity: ContactEntity) {
        contactDAO.update(mapToContactEntityForStorage(contactEntity))
    }

    override suspend fun deleteContact(contactEntity: ContactEntity) {
        contactDAO.delete(contactEntity = mapToContactEntityForStorage(contactEntity))
    }

    override suspend fun addContact(contactEntity: ContactEntity) {
        contactDAO.insert(mapToContactEntityForStorage(contactEntity))
    }

    override suspend fun getCountContacts(): Int =
        contactDAO.getCountContacts()

    override suspend fun clearContacts() {
        contactDAO.clearContacts()
    }

    override suspend fun getContacts(): List<ContactEntity> {
        val contacts = contactDAO.getContacts()
        return contacts.map { mapToContactEntity(it) }
    }

    override fun mapToContactEntityForStorage(contactEntity: ContactEntity): ContactEntityFromDatabase =
        ContactEntityFromDatabase(
            id = contactEntity.id,
            name = contactEntity.name,
            phone = contactEntity.phone
        )

    private fun mapToContactEntity(contactEntityFromDatabase: ContactEntityFromDatabase): ContactEntity =
        ContactEntity(
            id = contactEntityFromDatabase.id,
            name = contactEntityFromDatabase.name,
            phone = contactEntityFromDatabase.phone
        )
}