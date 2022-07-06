package com.example.martynov.ui.instruction.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.martynov.R
import com.example.martynov.ui.instruction.InstructionConstants

class HistoryInstructionFragment : BaseHistoryInstructionFragment() {

    override val tabName = InstructionConstants.HISTORY_TAB_NAME
    override val description = InstructionConstants.HISTORY_INSTRUCTION

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