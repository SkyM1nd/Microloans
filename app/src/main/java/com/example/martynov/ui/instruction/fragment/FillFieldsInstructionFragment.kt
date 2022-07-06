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
import com.google.android.material.textfield.TextInputEditText

class FillFieldsInstructionFragment : BaseInstructionFragment() {

    override val tabName = InstructionConstants.FILL_FIELDS_TAB_NAME
    override val description = InstructionConstants.FILL_FIELDS_INSTRUCTION

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_new_loan, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialToolbar>(R.id.toolbar).title =
            "" + view.findViewById<MaterialToolbar>(R.id.toolbar).title + Constants.INSTRUCTION_TITLE

        view.findViewById<TextInputEditText>(R.id.amount).apply {
            setText(context.getString(R.string.amount_example))
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.firstName).apply {
            setText(context.getString(R.string.name_example))
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.lastName).apply {
            setText(context.getString(R.string.last_name_example))
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.percent).apply {
            setText(context.getString(R.string.percent_example))
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.period).apply {
            setText(context.getString(R.string.period_example))
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.phoneNumber).apply {
            setText(context.getString(R.string.phone_number_example))
            isFocusable = false
        }
    }
}