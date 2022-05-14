package com.example.martynov.presentation.fragments

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.martynov.R
import com.example.martynov.domain.entities.ContactEntity
import com.example.martynov.presentation.ContactsAdapter

open class BaseFragment : Fragment() {

    fun fillContacts(result: MutableList<ContactEntity>, contactsAdapter: ContactsAdapter) {
        activity?.runOnUiThread {
            activity?.findViewById<RecyclerView>(R.id.contactsRecyclerView)?.apply {
                contactsAdapter.contacts = result
                adapter = contactsAdapter
            }
        }
    }
}