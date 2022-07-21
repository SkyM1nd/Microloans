package com.example.martynov.ui.instruction.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.martynov.R
import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.domain.entity.State
import com.example.martynov.presentation.LoanAdapter
import com.example.martynov.utils.Constants
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

open class BaseHistoryInstructionFragment : BaseInstructionFragment() {

    val listItem = listOf(
        LoanEntity(
            10000,
            date = Date(),
            firstName = "Иван",
            id = 1,
            lastName = "Иванов",
            percent = 5.6,
            period = 10,
            phoneNumber = "+88005553535",
            state = State.APPROVED
        ),
        LoanEntity(
            7000,
            date = Date(),
            firstName = "Иван",
            id = 1,
            lastName = "Иванов",
            percent = 7.2,
            period = 20,
            phoneNumber = "+88005553535",
            state = State.REGISTERED
        ),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.newLoanButton).apply {
            isEnabled = false
        }

        view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh).isEnabled = false

        view.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            title =
                "" + view.findViewById<MaterialToolbar>(R.id.toolbar).title +
                        Constants.INSTRUCTION_TITLE
        }
    }

    open fun setDataInRecyclerView(view: View, list: List<LoanEntity>) {
        view.findViewById<RecyclerView>(R.id.loanRecyclerView).apply {
            val loanAdapter = LoanAdapter(onClick = {})
            loanAdapter.loans = list
            adapter = loanAdapter
        }
    }
}