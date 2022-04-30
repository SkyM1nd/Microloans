package com.example.martynov

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactsViewModel: ViewModel() {

    private val _contactsData = MutableLiveData<List<ContactEntity>>()
    val contactsData: LiveData<List<ContactEntity>> = _contactsData

    fun loadContacts(contactsList:List<ContactEntity>) {
        _contactsData.value = contactsList
    }


}