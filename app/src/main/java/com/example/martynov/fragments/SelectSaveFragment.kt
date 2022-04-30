package com.example.martynov.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.martynov.R

class SelectSaveFragment : Fragment(R.layout.fragment_select_save) {

    companion object {

        fun newInstance(): SelectSaveFragment =
            SelectSaveFragment().apply { }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
