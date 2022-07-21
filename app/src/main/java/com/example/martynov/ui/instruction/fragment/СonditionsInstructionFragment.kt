package com.example.martynov.ui.instruction.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.martynov.R
import com.example.martynov.ui.instruction.InstructionConstants
import com.example.martynov.utils.Constants
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class ConditionsInstructionFragment : BaseInstructionFragment() {

    override val tabName = InstructionConstants.CONDITIONS_TAB_NAME
    override val description = InstructionConstants.CONDITIONS_INSTRUCTION

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_new_loan, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<ConstraintLayout>(R.id.popupWindowConditions).isVisible =
            true

        view.findViewById<MaterialToolbar>(R.id.toolbar).title =
            "" + view.findViewById<MaterialToolbar>(R.id.toolbar).title +
                    Constants.INSTRUCTION_TITLE

        view.findViewById<ScrollView>(R.id.newLoanScrollView).foreground =
            ContextCompat.getDrawable(requireContext(), R.drawable.shadow_box)

        view.findViewById<FloatingActionButton>(R.id.createLoanButton).apply {
            isEnabled = false
        }

        view.findViewById<TextInputEditText>(R.id.amount).apply {
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.firstName).apply {
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.lastName).apply {
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.percent).apply {
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.period).apply {
            isFocusable = false
        }
        view.findViewById<TextInputEditText>(R.id.phoneNumber).apply {
            isFocusable = false
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<ConstraintLayout>(R.id.popupWindowConditions).isVisible =
            true
    }

    override fun onPause() {
        super.onPause()
        requireActivity().findViewById<ConstraintLayout>(R.id.popupWindowConditions).isVisible =
            false
    }
}