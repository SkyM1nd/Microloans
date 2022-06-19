package com.example.martynov.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.martynov.R
import com.example.martynov.utils.Constants
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ConditionsInstructionFragment : Fragment() {

    companion object {
        fun newInstance(): ConditionsInstructionFragment =
            ConditionsInstructionFragment().apply { }
    }

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
            "" + view.findViewById<MaterialToolbar>(R.id.toolbar).title + Constants.INSTRUCTION_TITLE

        view.findViewById<ScrollView>(R.id.newLoanScrollView).foreground =
            ContextCompat.getDrawable(requireContext(), R.drawable.shadow_box)

        view.findViewById<FloatingActionButton>(R.id.createLoanButton).apply {
            isEnabled = false
        }
    }
}