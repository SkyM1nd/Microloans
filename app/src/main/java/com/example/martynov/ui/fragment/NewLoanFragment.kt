package com.example.martynov.ui.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.R
import com.example.martynov.app.App
import com.example.martynov.presentation.HandlerResult
import com.example.martynov.presentation.viewmodel.NewLoanViewModel
import com.example.martynov.presentation.viewmodel.NewLoanViewModelFactory
import com.example.martynov.ui.snackbar.LoanSnackbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject


class NewLoanFragment : Fragment(R.layout.fragment_new_loan) {

    @Inject
    lateinit var newLoanViewModelFactory: NewLoanViewModelFactory

    private val newLoanViewModel: NewLoanViewModel by lazy {
        ViewModelProvider(this, newLoanViewModelFactory)[NewLoanViewModel::class.java]
    }

    private val myCoordinatorLayout: CoordinatorLayout by lazy {
        requireActivity().findViewById(
            R.id.loanCoordinatorLayout
        )
    }
    private val loanSnackbar: LoanSnackbar by lazy { LoanSnackbar(myCoordinatorLayout) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            newLoanViewModel.setProgressBarVisible(true)
            newLoanViewModel.getLoanConditions()
        }

        initListener(view)
        initObserve(view)
    }

    private fun initListener(view: View) {
        val amount = view.findViewById<TextInputEditText>(R.id.amount)
        amount.addTextChangedListener {
            newLoanViewModel.setAmount(amount.text.toString())
        }

        val firstName = view.findViewById<TextInputEditText>(R.id.firstName)
        firstName.addTextChangedListener {
            newLoanViewModel.setFirstName(firstName.text.toString())
        }

        val lastName = view.findViewById<TextInputEditText>(R.id.lastName)
        lastName.addTextChangedListener {
            newLoanViewModel.setLastName(lastName.text.toString())
        }

        val percent = view.findViewById<TextInputEditText>(R.id.percent)
        percent.addTextChangedListener {
            newLoanViewModel.setPercent(percent.text.toString())
        }

        val period = view.findViewById<TextInputEditText>(R.id.period)
        period.addTextChangedListener {
            newLoanViewModel.setPeriod(period.text.toString())
        }

        val phoneNumber = view.findViewById<TextInputEditText>(R.id.phoneNumber)
        phoneNumber.addTextChangedListener {
            newLoanViewModel.setPhoneNumber(phoneNumber.text.toString())
        }

        view.findViewById<FloatingActionButton>(R.id.createLoanButton).setOnClickListener {
            newLoanViewModel.createNewLoan()
        }
    }

    private fun initObserve(view: View) {
        newLoanViewModel.error.observe(viewLifecycleOwner) {
            newLoanViewModel.setProgressBarVisible(false)
            loanSnackbar.showSnackbarToUnknownError()
        }

        newLoanViewModel.resultGetLoanConditions.observe(viewLifecycleOwner) { result ->
            object : HandlerResult(
                requireContext(),
                newLoanViewModel,
                loanSnackbar
            ) {
                override fun resultSuccess() {
                    newLoanViewModel.setProgressBarVisible(false)

                    view.findViewById<TextInputEditText>(R.id.percent)
                        .setText(result.data?.percent.toString())
                    view.findViewById<TextInputEditText>(R.id.period)
                        .setText(result.data?.period.toString())

                    val dialog = Dialog(requireContext())
                    dialog.setContentView(R.layout.popup_window_conditions)
                    dialog.findViewById<TextView>(R.id.amountMax).text =
                        result.data?.maxAmount.toString()
                    dialog.findViewById<TextView>(R.id.currentPercent).text =
                        result.data?.percent.toString()
                    dialog.findViewById<TextView>(R.id.currentPeriod).text =
                        result.data?.period.toString()
                    dialog.show()
                }
            }(result)
        }

        newLoanViewModel.resultCreateNewLoan.observe(viewLifecycleOwner) { result ->
            object : HandlerResult(
                requireContext(),
                newLoanViewModel,
                loanSnackbar
            ) {
                override fun resultSuccess() {
                    newLoanViewModel.setProgressBarVisible(false)
                    newLoanViewModel.navigateToSuccess()
                }
            }(result)
        }

        newLoanViewModel.progressBarVisible.observe(viewLifecycleOwner) {
            requireActivity().findViewById<ProgressBar>(R.id.newLoanProgressBar).isVisible = it
        }

        newLoanViewModel.errorEmptyFields.observe(viewLifecycleOwner) {
            newLoanViewModel.setProgressBarVisible(false)
            loanSnackbar.showSnackbarToEmptyFields()
        }

        newLoanViewModel.errorAmountBelowZero.observe(viewLifecycleOwner) {
            newLoanViewModel.setProgressBarVisible(false)
            loanSnackbar.showSnackbarToAmountZero()
        }

        newLoanViewModel.errorIncorrectData.observe(viewLifecycleOwner) {
            newLoanViewModel.setProgressBarVisible(false)
            loanSnackbar.showSnackbarToIncorrectData()
        }
    }
}