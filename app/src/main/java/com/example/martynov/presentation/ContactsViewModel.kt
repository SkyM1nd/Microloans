package com.example.martynov.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.martynov.domain.entities.ConfigDataSource
import com.example.martynov.domain.entities.ContactEntity
import com.example.martynov.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable

class ContactsViewModel(
    private val getContactsUseCase: GetContactsUseCase,
    private val clearContactsUseCase: ClearContactsUseCase,
    private val editContactUseCase: EditContactUseCase,
    private val addContactUseCase: AddContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase
) : ViewModel(), Serializable {

    private val _contactsData = MutableLiveData<List<ContactEntity>>()
    val contactsData: LiveData<List<ContactEntity>> = _contactsData

    private val _config = MutableLiveData<ConfigDataSource>()
    val config: LiveData<ConfigDataSource> = _config

    private val _id = MutableLiveData<Int>()
    val id: LiveData<Int> = _id

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    fun getContacts() {
        viewModelScope.launch(Dispatchers.Default) {
            _contactsData.postValue(getContactsUseCase.execute(_config.value!!))
        }

    }

    fun clearContacts() {
        viewModelScope.launch(Dispatchers.Default) {
            _contactsData.postValue(clearContactsUseCase.execute(_config.value!!))
        }
    }

    fun editContact(contactEntity: ContactEntity) {
        viewModelScope.launch(Dispatchers.Default) {
            _contactsData.postValue(
                editContactUseCase.execute(
                    _config.value!!,
                    ContactEntity(
                        id = _id.value!!,
                        name = contactEntity.name,
                        phone = contactEntity.phone
                    )
                )
            )
        }
    }

    fun deleteContact(contactEntity: ContactEntity) {
        viewModelScope.launch(Dispatchers.Default) {
            _contactsData.postValue(
                deleteContactUseCase.execute(
                    _config.value!!, ContactEntity(
                        id = _id.value!!,
                        name = contactEntity.name,
                        phone = contactEntity.phone
                    )
                )
            )
        }
    }

    fun addContact(contactEntity: ContactEntity) {
        viewModelScope.launch(Dispatchers.Default) {
            _contactsData.postValue(
                addContactUseCase.execute(
                    _config.value!!, ContactEntity(
                        id = contactEntity.id,
                        name = contactEntity.name,
                        phone = contactEntity.phone
                    )
                )
            )
        }
    }

    fun setContact(id: Int, name: String, phone: String) {
        _id.value = id
        _name.value = name
        _phone.value = phone
    }

    fun setConfig(configDataSource: ConfigDataSource) {
        _config.value = configDataSource
    }

}