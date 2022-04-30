package com.example.martynov.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.martynov.ContactEntity
import com.example.martynov.ContactsImpl
import com.example.martynov.ContactsViewModel
import com.example.martynov.R
import com.example.martynov.database.AppDataBase

class DatabaseFragment : BaseFragment(), ContactsImpl {
    companion object {
        const val APP_PREFERENCES = "mySettings"

        const val APP_PREFERENCES_COUNTER_KEY = "first_entry_db"

        fun newInstance(): DatabaseFragment =
            DatabaseFragment().apply { }

    }

    private val viewModel: ContactsViewModel by viewModels()
    private lateinit var database: AppDataBase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_database, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.contactsData.observe(viewLifecycleOwner, Observer { result ->
            fillContacts(result as MutableList<ContactEntity>)
        })

        val clearDatabaseButton = view.findViewById<Button>(R.id.clearButtonFragment)
        clearDatabaseButton.setOnClickListener { clearContacts() }

        database = AppDataBase.getAppDatabase(requireContext())

        if (activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                ?.getBoolean(APP_PREFERENCES_COUNTER_KEY, true)!!
        ) {
            requestData()
        } else {
            fillContacts(database.contactDAO.getContacts() as MutableList<ContactEntity>)
            //viewModel.loadContacts(database.contactDAO.getContacts())

        }
    }

    private fun clearContacts() {
        database.contactDAO.clearContacts()
        contactsAdapter.clear()
    }

    private fun requestData() {
        task = executor.submit<Any> {
            val result = try {
                retrieveContacts(requireContext())
            } catch (error: NullPointerException) {
                error.message
            }
            (result as MutableList<ContactEntity>).forEach { database.contactDAO.insert(it) }

            fillContacts(result)
            //viewModel.loadContacts(result)

            activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                ?.edit()
                ?.putBoolean(APP_PREFERENCES_COUNTER_KEY, false)
                ?.apply()
        }
    }
}