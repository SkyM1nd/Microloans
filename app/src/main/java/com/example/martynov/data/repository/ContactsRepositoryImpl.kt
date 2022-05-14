package com.example.martynov.data.repository

import com.example.martynov.data.storage.ContactsStorage
import com.example.martynov.data.storage.database.DatabaseContactsStorage
import com.example.martynov.data.storage.file.FileContactsStorage
import com.example.martynov.data.storage.phonebook.PhoneBookStorage
import com.example.martynov.data.storage.sharedpreferences.SharedPreferencesStorage
import com.example.martynov.domain.entities.ConfigDataSource
import com.example.martynov.domain.entities.ContactEntity
import com.example.martynov.domain.repository.ContactsRepository

class ContactsRepositoryImpl(
    private val databaseContactsStorage: DatabaseContactsStorage,
    private val fileContactsStorage: FileContactsStorage,
    private val phoneBookStorage: PhoneBookStorage,
    private val sharedPreferencesStorage: SharedPreferencesStorage
) : ContactsRepository {

    override suspend fun getContacts(configDataSource: ConfigDataSource): List<ContactEntity> =
        when (configDataSource) {
            ConfigDataSource.DATABASE -> getData(databaseContactsStorage)
            ConfigDataSource.FILE -> getData(fileContactsStorage)
        }

    private suspend fun getData(contactsStorage: ContactsStorage): List<ContactEntity> {
        val result: List<ContactEntity>

        if (!sharedPreferencesStorage.getStateFlag(contactsStorage.getPreferencesName())) {
            result = phoneBookStorage.retrieveContacts()
            result.forEach { contactsStorage.addContact(it) }
            sharedPreferencesStorage.addFlag(contactsStorage.getPreferencesName())
        } else {
            result = contactsStorage.getContacts()
        }
        return result
    }

    override suspend fun clearContacts(configDataSource: ConfigDataSource): List<ContactEntity> =
        when (configDataSource) {
            ConfigDataSource.DATABASE -> clearContactsFromStorage(databaseContactsStorage)
            ConfigDataSource.FILE -> clearContactsFromStorage(fileContactsStorage)
        }

    override suspend fun editContact(
        configDataSource: ConfigDataSource,
        contactEntity: ContactEntity
    ): List<ContactEntity> =
        when (configDataSource) {
            ConfigDataSource.DATABASE -> editContactFromStorage(
                databaseContactsStorage,
                contactEntity
            )
            ConfigDataSource.FILE -> editContactFromStorage(fileContactsStorage, contactEntity)
        }

    override suspend fun addContact(
        configDataSource: ConfigDataSource,
        contactEntity: ContactEntity
    ): List<ContactEntity> =
        when (configDataSource) {
            ConfigDataSource.DATABASE -> addContactInStorage(
                databaseContactsStorage,
                contactEntity
            )
            ConfigDataSource.FILE -> addContactInStorage(
                fileContactsStorage,
                contactEntity
            )
        }

    private suspend fun addContactInStorage(
        contactsStorage: ContactsStorage,
        contactEntity: ContactEntity
    ): List<ContactEntity> {
        val contacts = contactsStorage.getContacts()

        var id = -1
        if (contacts.isEmpty()) {
            id = 0
        } else {
            id = contacts[contacts.size - 1].id + 1
        }

        contactsStorage.addContact(
            ContactEntity(
                id = id,
                name = contactEntity.name,
                phone = contactEntity.phone
            )
        )
        return contactsStorage.getContacts()
    }

    override suspend fun deleteContact(
        configDataSource: ConfigDataSource,
        contactEntity: ContactEntity
    ): List<ContactEntity> =
        when (configDataSource) {
            ConfigDataSource.DATABASE -> deleteContactInStorage(
                databaseContactsStorage,
                contactEntity
            )
            ConfigDataSource.FILE -> deleteContactInStorage(
                fileContactsStorage,
                contactEntity
            )
        }

    private suspend fun deleteContactInStorage(
        contactsStorage: ContactsStorage,
        contactEntity: ContactEntity
    ): List<ContactEntity> {
        contactsStorage.deleteContact(contactEntity)
        return contactsStorage.getContacts()
    }

    private suspend fun editContactFromStorage(
        contactsStorage: ContactsStorage,
        contactEntity: ContactEntity
    ): List<ContactEntity> {
        contactsStorage.editContact(contactEntity = contactEntity)
        return contactsStorage.getContacts()
    }

    private suspend fun clearContactsFromStorage(contactsStorage: ContactsStorage): List<ContactEntity> {
        contactsStorage.clearContacts()
        return mutableListOf()
    }
}