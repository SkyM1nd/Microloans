package com.example.martynov.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.martynov.R
import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.presentation.instruction.InstructionLoanAdapter

class OpenDetailInstructionFragment : BaseInstructionFragment() {

    companion object {
        fun newInstance(): OpenDetailInstructionFragment =
            OpenDetailInstructionFragment().apply { }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_loan_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataInRecyclerView(view, listItem)
    }

    override fun setDataInRecyclerView(view: View, list: List<LoanEntity>) {
        view.findViewById<RecyclerView>(R.id.loanRecyclerView).apply {
            val loanAdapter = InstructionLoanAdapter()
            loanAdapter.loans = list
            adapter = loanAdapter
        }
    }
}