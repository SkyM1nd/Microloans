package com.example.martynov.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.R
import com.example.martynov.app.App
import com.example.martynov.domain.entities.ConfigDataSource
import com.example.martynov.domain.entities.ContactEntity
import com.example.martynov.presentation.ContactsViewModel
import com.example.martynov.presentation.ContactsViewModelFactory
import javax.inject.Inject

class AddFragment : Fragment() {

    companion object {

        fun newInstance(
            configDataSource: ConfigDataSource
        ): AddFragment =
            AddFragment().apply {
                this.configDataSource = configDataSource
            }
    }

    @Inject
    lateinit var viewModelFactory: ContactsViewModelFactory

    private lateinit var contactsViewModel: ContactsViewModel
    private lateinit var configDataSource: ConfigDataSource

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_add, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context?.applicationContext as App).appComponent.inject(this)
        contactsViewModel = ViewModelProvider(this, viewModelFactory)[ContactsViewModel::class.java]

        if (savedInstanceState == null) {

            contactsViewModel.setConfig(configDataSource)
        }

        val addButton = view.findViewById<Button>(R.id.addButtonFragment)
        addButton.setOnClickListener {
            contactsViewModel.addContact(
                ContactEntity(
                    -1,
                    view.findViewById<TextView>(R.id.editTextTextPersonName).text.toString(),
                    view.findViewById<TextView>(R.id.editTextPhone).text.toString()
                )
            )
            activity?.onBackPressed()
        }
    }
}