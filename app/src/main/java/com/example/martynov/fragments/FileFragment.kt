package com.example.martynov.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.example.martynov.ContactEntity
import com.example.martynov.ContactsImpl
import com.example.martynov.ContactsViewModel
import com.example.martynov.R
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


class FileFragment : BaseFragment(), ContactsImpl {

    companion object {
        const val FILE_NAME = "login_file"

        fun newInstance(): FileFragment =
            FileFragment().apply { }
    }

    private val viewModel: ContactsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_file, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clearDatabaseButton = view.findViewById<Button>(R.id.clearButtonFragment)
        clearDatabaseButton.setOnClickListener { clearContacts() }

        createFileIfNotExist()
    }

    private fun createFileIfNotExist() {
        val folder = File("${activity?.filesDir}")
        val file = File(folder.absolutePath + "/$FILE_NAME")
        if (!folder.exists()) {
            folder.mkdir()
        }
        if (!file.exists()) {
            file.createNewFile()
            requestData()
        } else {
            restoreState()
        }
    }

    private fun restoreState() {
        activity?.openFileInput(FILE_NAME).use { fileInputStream ->
            val objectInputStream = ObjectInputStream(fileInputStream)
            val result: MutableList<ContactEntity>? =
                objectInputStream.readObject() as MutableList<ContactEntity>?
            if (result != null) {
                //viewModel.loadContacts(result)
                fillContacts(result)
            }
        }
    }

    private fun saveState(contacts: MutableList<ContactEntity>?) {
        activity?.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use { fileOutputStream ->
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(contacts)
        }
    }

    private fun requestData() {
        task = executor.submit<Any> {
            val result = try {
                retrieveContacts(requireContext())
            } catch (error: NullPointerException) {
                error.message
            }
            saveState(result as MutableList<ContactEntity>)
//            viewModel.loadContacts(result)
            fillContacts(result)
        }
    }

    private fun clearContacts() {
        saveState(null)
        contactsAdapter.clear()
    }

}
