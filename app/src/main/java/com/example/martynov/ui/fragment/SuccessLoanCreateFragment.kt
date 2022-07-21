package com.example.martynov.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.R
import com.example.martynov.app.App
import com.example.martynov.presentation.viewmodel.NewLoanViewModel
import com.example.martynov.presentation.viewmodel.NewLoanViewModelFactory
import javax.inject.Inject

class SuccessLoanCreateFragment : Fragment(R.layout.fragment_success_create_loan) {

    @Inject
    lateinit var newLoanViewModelFactory: NewLoanViewModelFactory

    private val newLoanViewModel: NewLoanViewModel by lazy {
        ViewModelProvider(this, newLoanViewModelFactory)[NewLoanViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    newLoanViewModel.navigateToHistory()
                }
            })
    }
}