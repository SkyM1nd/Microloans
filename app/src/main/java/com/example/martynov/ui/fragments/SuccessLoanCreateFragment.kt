package com.example.martynov.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.martynov.R
import com.example.martynov.utils.Constants

class SuccessLoanCreateFragment : Fragment(R.layout.fragment_success_create_loan) {

    companion object {
        fun newInstance(): SuccessLoanCreateFragment =
            SuccessLoanCreateFragment().apply { }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, LoanHistoryFragment.newInstance())
                        .addToBackStack(Constants.ADD_LOAN_HISTORY_FRAGMENT)
                        .commit()
                }
            })
    }
}