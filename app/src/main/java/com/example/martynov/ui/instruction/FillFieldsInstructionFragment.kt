package com.example.martynov.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.martynov.R
import com.example.martynov.utils.Constants
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText

class FillFieldsInstructionFragment : Fragment() {

    companion object {
        fun newInstance(): FillFieldsInstructionFragment =
            FillFieldsInstructionFragment().apply { }
    }

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
            setText("15000")
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.firstName).apply {
            setText("Иван")
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.lastName).apply {
            setText("Иванов")
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.percent).apply {
            setText("9.5")
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.period).apply {
            setText("30")
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.phoneNumber).apply {
            setText("+88005553535")
            isFocusable = false
        }
    }
}