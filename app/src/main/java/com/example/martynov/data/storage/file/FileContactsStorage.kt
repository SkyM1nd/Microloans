package com.example.martynov.data.storage.file

import android.content.Context
import com.example.martynov.data.storage.ContactsStorage
import com.example.martynov.domain.entities.ContactEntity
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileContactsStorage(private val context: Context) : ContactsStorage {

    private val APP_PREFERENCES_NAME = "contacts_file"
    var file: File

    init {
        val folder = File("${context.filesDir}")
        file = File(folder.absolutePath + "/$APP_PREFERENCES_NAME")
        if (!folder.exists()) {
            folder.mkdir()
        }
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    override fun getPreferencesName(): String = APP_PREFERENCES_NAME

    override suspend fun editContact(contactEntity: ContactEntity) {
        val contacts = getContacts() as MutableList<ContactEntity>

        var id = -1
        contacts.forEach {
            if(it.id == contactEntity.id) id = contacts.indexOf(it)
        }

        contacts.set(id, contactEntity)
        saveFile(contacts)
    }

    override suspend fun deleteContact(contactEntity: ContactEntity) {
        val contacts = getContacts() as MutableList<ContactEntity>
        contacts.removeIf { it.id == contactEntity.id }

        saveFile(contacts)
    }

    override suspend fun getCountContacts(): Int = getContacts().size

    override suspend fun clearContacts() {
        saveFile(mutableListOf())
    }

    override suspend fun addContact(contactEntity: ContactEntity) {
        val contacts = getContacts()
        (contacts as MutableList<ContactEntity>).add(contactEntity)

        saveFile(contacts)
    }

    override suspend fun getContacts(): List<ContactEntity> {
        if (file.length() != 0L) {
            context.openFileInput(APP_PREFERENCES_NAME).use { fileInputStream ->
                val objectInputStream = ObjectInputStream(fileInputStream)
                val contacts: MutableList<ContactEntityFromFile> =
                    objectInputStream.readObject() as MutableList<ContactEntityFromFile>

                return contacts.map { mapToContactEntity(it) }
            }
        } else {
            return mutableListOf()
        }
    }

    private fun mapToContactEntity(contactEntityFromFile: ContactEntityFromFile): ContactEntity =
        ContactEntity(
            id = contactEntityFromFile.id,
            name = contactEntityFromFile.name,
            phone = contactEntityFromFile.phone
        )

    private fun saveFile(contacts: List<ContactEntity>) {
        context.openFileOutput(APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
            .use { fileOutputStream ->
                val objectOutputStream = ObjectOutputStream(fileOutputStream)
                objectOutputStream.writeObject(contacts.map { mapToContactEntityForStorage(it) })
            }
    }

    override fun mapToContactEntityForStorage(contactEntity: ContactEntity): ContactEntityFromFile =
        ContactEntityFromFile(
            id = contactEntity.id,
            name = contactEntity.name,
            phone = contactEntity.phone
        )
}