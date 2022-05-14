package com.example.martynov.data.storage.phonebook

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import com.example.martynov.domain.entities.ContactEntity

class PhoneBookStorage(private val context: Context) {

    @SuppressLint("Range")
    fun retrieveContacts(): List<ContactEntity> {
        val uri = ContactsContract.Contacts.CONTENT_URI

        val cursor = context.contentResolver?.query(
            uri,
            null,
            null,
            null,
            null

        )
        val contacts = mutableListOf<ContactEntity>()
        var id = 0

        cursor?.use {
            val indexName = cursor.getColumnIndex("display_name")
            val indexId = cursor.getColumnIndex("_id")

            while (cursor.moveToNext()) {
                val name = cursor.getString(indexName)
                val idContact = cursor.getString(indexId)
                val pCursor = context.contentResolver?.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds
                        .Phone.CONTACT_ID + " = ?",
                    arrayOf(idContact),
                    null
                )
                var phone = ""
                while (pCursor!!.moveToNext()) {
                    phone = pCursor.getString(
                        pCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                    )
                }
                val contact = ContactEntity(id = id, name = name, phone = phone)
                contacts.add(contact)
                id++
            }
        }
        return contacts
    }
}