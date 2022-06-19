package com.example.martynov.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.martynov.R
import com.example.martynov.app.App
import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.presentation.HandlerResult
import com.example.martynov.presentation.LoanAdapter
import com.example.martynov.presentation.viewmodel.LoanViewModel
import com.example.martynov.presentation.viewmodel.LoanViewModelFactory
import com.example.martynov.ui.LoginActivity
import com.example.martynov.ui.instruction.InstructionActivity
import com.example.martynov.ui.snackbar.LoanSnackbar
import com.example.martynov.utils.Constants
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject
import kotlin.system.exitProcess


class LoanHistoryFragment : Fragment(R.layout.fragment_loan_history) {

    companion object {
        fun newInstance(): LoanHistoryFragment =
            LoanHistoryFragment().apply { }
    }

    @Inject
    lateinit var loanViewModelFactory: LoanViewModelFactory

    private val loanViewModel: LoanViewModel by lazy {
        ViewModelProvider(this, loanViewModelFactory).get(LoanViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myCoordinatorLayout =
            requireActivity().findViewById<CoordinatorLayout>(R.id.loanCoordinatorLayout)
        val loanSnackbar = LoanSnackbar(myCoordinatorLayout)

        view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh).setOnRefreshListener {
            updateLoan()
        }

        view.findViewById<FloatingActionButton>(R.id.newLoanButton).setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, NewLoanFragment.newInstance())
                .addToBackStack(Constants.ADD_NEW_LOAN_FRAGMENT)
                .commit()
        }

        view.findViewById<MaterialToolbar>(R.id.toolbar).setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_refresh -> {
                    requireActivity().findViewById<SwipeRefreshLayout>(R.id.swiperefresh).isRefreshing =
                        true
                    updateLoan()
                    true
                }
                R.id.menu_instruction -> {
                    requireContext().startActivity(
                        Intent(
                            requireContext(),
                            InstructionActivity::class.java
                        )
                    )
                    true
                }
                R.id.menu_exit -> {
                    loanViewModel.deleteToken()
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    requireContext().startActivity(intent)
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            loanViewModel.getAllLoans()
        }

        loanViewModel.setNotFirstLogin()

        loanViewModel.loans.observe(viewLifecycleOwner) {
            setDataInRecyclerView(it)
        }

        loanViewModel.error.observe(viewLifecycleOwner) {
            loanViewModel.setProgressBarVisible(false)
            loanSnackbar.showSnackbarToUnknownError()
        }

        loanViewModel.progressBarVisible.observe(viewLifecycleOwner) {
            requireActivity().findViewById<ProgressBar>(R.id.loanProgressBar).isVisible = it
        }

        loanViewModel.resultGetAllLoans.observe(viewLifecycleOwner) { result ->
            object : HandlerResult(
                requireContext(),
                loanViewModel,
                loanSnackbar
            ) {
                override fun resultSuccess() {
                    loanViewModel.setProgressBarVisible(false)
                    loanViewModel.setLoans(requireNotNull(result.data))
                }
            }(result)
        }

        loanViewModel.resultGetLoanDetail.observe(viewLifecycleOwner) { result ->
            object :
                HandlerResult(
                    requireContext(),
                    loanViewModel,
                    loanSnackbar
                ) {
                override fun resultSuccess() {
                    loanViewModel.setProgressBarVisible(false)

                    requireActivity().intent.putExtra(Constants.LOAN_DETAIL, result.data)

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, LoanDetailFragment.newInstance())
                        .addToBackStack(Constants.ADD_LOAN_DETAIL_FRAGMENT)
                        .commit()
                }
            }(result)
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finishAffinity()
                    exitProcess(0)
                }
            })
    }

    private fun updateLoan() {
        loanViewModel.getAllLoans()
        requireActivity().findViewById<SwipeRefreshLayout>(R.id.swiperefresh).isRefreshing = false
    }

    private fun setDataInRecyclerView(list: List<LoanEntity>) {
        requireActivity().findViewById<RecyclerView>(R.id.loanRecyclerView).apply {
            val loanAdapter = LoanAdapter(onClick = {
                loanViewModel.getLoanDetail(it.id)
            })
            loanAdapter.loans = list
            adapter = loanAdapter
        }
    }
}