package com.example.martynov.utils

import android.content.Intent
import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.ui.LoanActivity
import com.example.martynov.ui.LoginActivity
import com.example.martynov.ui.fragment.LoanDetailFragment
import com.example.martynov.ui.fragment.LoanHistoryFragment
import com.example.martynov.ui.fragment.NewLoanFragment
import com.example.martynov.ui.fragment.SuccessLoanCreateFragment
import com.example.martynov.ui.instruction.InstructionActivity
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screen {

    fun detail(loanEntity: LoanEntity) =
        FragmentScreen { LoanDetailFragment.newInstance(loanEntity) }

    fun history() = FragmentScreen { LoanHistoryFragment() }

    fun newLoan() = FragmentScreen { NewLoanFragment() }

    fun successLoan() = FragmentScreen { SuccessLoanCreateFragment() }

    fun login() = ActivityScreen { Intent(it, LoginActivity::class.java) }

    fun loan() = ActivityScreen { Intent(it, LoanActivity::class.java) }

    fun instruction() = ActivityScreen { Intent(it, InstructionActivity::class.java) }
}
