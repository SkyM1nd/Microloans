package com.example.martynov.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.martynov.R

class HistoryInstructionFragment : BaseInstructionFragment() {

    companion object {
        fun newInstance(): HistoryInstructionFragment =
            HistoryInstructionFragment().apply { }
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
}