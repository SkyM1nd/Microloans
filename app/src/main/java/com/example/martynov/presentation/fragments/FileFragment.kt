package com.example.martynov.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.R
import com.example.martynov.app.App
import com.example.martynov.domain.entities.ConfigDataSource
import com.example.martynov.domain.entities.ContactEntity
import com.example.martynov.presentation.ContactsAdapter
import com.example.martynov.presentation.ContactsViewModel
import com.example.martynov.presentation.ContactsViewModelFactory
import javax.inject.Inject

class FileFragment : BaseFragment() {

    companion object {

        fun newInstance(): FileFragment =
            FileFragment().apply {
            }
    }

    @Inject
    lateinit var viewModelFactory: ContactsViewModelFactory

    private lateinit var contactsViewModel: ContactsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_file, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context?.applicationContext as App).appComponent.inject(this)
        contactsViewModel = ViewModelProvider(this, viewModelFactory)[ContactsViewModel::class.java]

        contactsViewModel.setConfig(ConfigDataSource.FILE)

        val contactsAdapter = ContactsAdapter(onClick = {
            val fragmentManager = activity?.supportFragmentManager
            val transactionName = "Add EditorFragment"
            val fragment = EditorFragment.newInstance(
                it,
                ConfigDataSource.FILE
            )

            fragmentManager!!.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(transactionName)
                .commit()
        })

        contactsViewModel.contactsData.observe(viewLifecycleOwner, Observer { contacts ->
            fillContacts(contacts as MutableList<ContactEntity>, contactsAdapter)
        })

        val clearDatabaseButton = view.findViewById<Button>(R.id.clearButtonFragment)
        clearDatabaseButton.setOnClickListener { contactsViewModel.clearContacts() }

        val addDatabaseButton = view.findViewById<Button>(R.id.addButtonFragment)
        addDatabaseButton.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val transactionName = "Add AddFragment"
            val fragment = AddFragment.newInstance(
                ConfigDataSource.FILE
            )

            fragmentManager!!.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(transactionName)
                .commit()
        }

        contactsViewModel.getContacts()
    }

}
