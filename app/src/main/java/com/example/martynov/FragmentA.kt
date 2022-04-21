package com.example.martynov

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentA : Fragment(R.layout.fragment_a) {

    companion object {

        fun newInstance(): FragmentA =
            FragmentA().apply { }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val replaceButton = view.findViewById<Button>(R.id.replaceButtonFragmentA)
        val fragmentManager = activity?.supportFragmentManager
        replaceButton.setOnClickListener { replaceFragment(fragmentManager!!) }

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finishAndRemoveTask()
                }
            })
    }

    private fun replaceFragment(fragmentManager: FragmentManager) {
        val transactionName = "ReplaceA"
        val fragment = FragmentB.newInstance()

        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(transactionName)
            .commit()
    }

}