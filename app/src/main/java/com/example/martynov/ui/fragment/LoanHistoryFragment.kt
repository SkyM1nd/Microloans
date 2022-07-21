package com.example.martynov.ui.fragment

import android.content.Context
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
import com.example.martynov.ui.snackbar.LoanSnackbar
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject
import kotlin.system.exitProcess

class LoanHistoryFragment : Fragment(R.layout.fragment_loan_history) {

    @Inject
    lateinit var loanViewModelFactory: LoanViewModelFactory

    private val loanViewModel: LoanViewModel by lazy {
        ViewModelProvider(this, loanViewModelFactory)[LoanViewModel::class.java]
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
            loanViewModel.getAllLoans()
        }

        loanViewModel.setNotFirstLogin()

        initListener(view)
        initObserve()
    }

    private fun initListener(view: View) {
        view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh).setOnRefreshListener {
            updateLoan()
        }

        view.findViewById<FloatingActionButton>(R.id.newLoanButton).setOnClickListener {
            loanViewModel.navigateToNewLoan()
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
                    loanViewModel.navigateToInstruction()
                    true
                }
                R.id.menu_exit -> {
                    loanViewModel.deleteToken()
                    loanViewModel.navigateToLogin()
                    true
                }
                else -> false
            }
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

    private fun initObserve() {
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
                    loanViewModel.navigateToDetail(requireNotNull(result.data))
                }
            }(result)
        }
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