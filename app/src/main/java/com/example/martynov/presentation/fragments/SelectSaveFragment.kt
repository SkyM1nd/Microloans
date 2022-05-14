package com.example.martynov.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.R
import com.example.martynov.app.App
import com.example.martynov.presentation.ContactsViewModel
import com.example.martynov.presentation.ContactsViewModelFactory
import javax.inject.Inject

class SelectSaveFragment : Fragment(R.layout.fragment_select_save) {

    companion object {

        fun newInstance(): SelectSaveFragment =
            SelectSaveFragment().apply {}
    }

    @Inject
    lateinit var viewModelFactory: ContactsViewModelFactory

    private lateinit var contactsViewModel: ContactsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_select_save, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context?.applicationContext as App).appComponent.inject(this)
        contactsViewModel = ViewModelProvider(this, viewModelFactory)[ContactsViewModel::class.java]

        val databaseButton = view.findViewById<Button>(R.id.selectDatabaseButtonFragment)
        val fileButton = view.findViewById<Button>(R.id.selectFileButtonFragment)
        val fragmentManager = activity?.supportFragmentManager

        databaseButton.setOnClickListener { selectDatabase(fragmentManager!!) }
        fileButton.setOnClickListener { selectFile(fragmentManager!!) }

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finishAndRemoveTask()
                }
            })
    }

    private fun selectDatabase(fragmentManager: FragmentManager) {
        val transactionName = "Add DatabaseFragment"
        val fragment = DatabaseFragment.newInstance()

        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(transactionName)
            .commit()
    }

    private fun selectFile(fragmentManager: FragmentManager) {
        val transactionName = "Add FileFragment"
        val fragment = FileFragment.newInstance()

        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(transactionName)
            .commit()
    }

}
