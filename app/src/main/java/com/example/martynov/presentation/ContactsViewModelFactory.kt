package com.example.martynov.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.domain.usecases.*

class ContactsViewModelFactory(
    private val getContactsUseCase: GetContactsUseCase,
    private val clearContactsUseCase: ClearContactsUseCase,
    private val editContactUseCase: EditContactUseCase,
    private val addContactUseCase: AddContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsViewModel(
            getContactsUseCase = getContactsUseCase,
            clearContactsUseCase = clearContactsUseCase,
            editContactUseCase = editContactUseCase,
            addContactUseCase = addContactUseCase,
            deleteContactUseCase = deleteContactUseCase
        ) as T
    }
}