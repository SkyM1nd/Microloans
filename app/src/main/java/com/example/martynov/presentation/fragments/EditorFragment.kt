package com.example.martynov.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.R
import com.example.martynov.app.App
import com.example.martynov.domain.entities.ConfigDataSource
import com.example.martynov.domain.entities.ContactEntity
import com.example.martynov.presentation.ContactsViewModel
import com.example.martynov.presentation.ContactsViewModelFactory
import javax.inject.Inject

class EditorFragment : Fragment() {

    companion object {

        fun newInstance(
            contactEntity: ContactEntity,
            configDataSource: ConfigDataSource
        ): EditorFragment =
            EditorFragment().apply {
                this.configDataSource = configDataSource
                this.contactEntity = contactEntity
            }
    }

    @Inject
    lateinit var viewModelFactory: ContactsViewModelFactory

    private lateinit var contactsViewModel: ContactsViewModel
    private lateinit var contactEntity: ContactEntity
    private lateinit var configDataSource: ConfigDataSource

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context?.applicationContext as App).appComponent.inject(this)
        contactsViewModel = ViewModelProvider(this, viewModelFactory)[ContactsViewModel::class.java]

        if (savedInstanceState == null) {
            contactsViewModel.setContact(
                id = contactEntity.id,
                name = contactEntity.name,
                phone = contactEntity.phone
            )

            contactsViewModel.setConfig(configDataSource)
        }

        contactsViewModel.name.observe(viewLifecycleOwner, Observer { name ->
            view.findViewById<TextView>(R.id.editTextTextPersonName).text = name
        })

        contactsViewModel.phone.observe(viewLifecycleOwner, Observer { phone ->
            view.findViewById<TextView>(R.id.editTextPhone).text = phone
        })

        val editButton = view.findViewById<Button>(R.id.addButtonFragment)
        editButton.setOnClickListener {
            contactsViewModel.editContact(
                ContactEntity(
                    -1,
                    view.findViewById<TextView>(R.id.editTextTextPersonName).text.toString(),
                    view.findViewById<TextView>(R.id.editTextPhone).text.toString()
                )
            )
            activity?.onBackPressed()
        }

        val deleteButton = view.findViewById<Button>(R.id.deleteButtonFragment)
        deleteButton.setOnClickListener {
            contactsViewModel.deleteContact(
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