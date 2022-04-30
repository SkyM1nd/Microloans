package com.example.martynov

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract

interface ContactsImpl {

    @SuppressLint("Range")
    fun retrieveContacts(context: Context): List<ContactEntity> {
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
            val index = cursor.getColumnIndex("display_name")

            while (cursor.moveToNext()) {
                val name = cursor.getString(index)
                val idContact = cursor.getString(39)
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

