package com.example.martynov.ui.instruction.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.martynov.R
import com.example.martynov.ui.instruction.InstructionConstants
import com.example.martynov.utils.Constants
import com.google.android.material.appbar.MaterialToolbar

class SuccessLoanCreateInstructionFragment : BaseInstructionFragment() {

    override val tabName = InstructionConstants.SUCCESS_LOAN_TAB_NAME
    override val description = InstructionConstants.SUCCESS_LOAN_INSTRUCTION

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_success_create_loan, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialToolbar>(R.id.SuccessLoanCreateToolbar).title =
            "" + view.findViewById<MaterialToolbar>(R.id.SuccessLoanCreateToolbar).title + Constants.INSTRUCTION_TITLE
    }
}