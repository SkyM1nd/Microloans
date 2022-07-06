package com.example.martynov.ui.instruction.fragment

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.martynov.R
import com.example.martynov.domain.entity.State
import com.example.martynov.ui.instruction.InstructionConstants
import com.example.martynov.utils.Constants
import com.google.android.material.appbar.MaterialToolbar
import java.util.*

class DetailInstructionFragment : BaseInstructionFragment() {

    override val tabName = InstructionConstants.DETAIL_TAB_NAME
    override val description = InstructionConstants.DETAIL_INSTRUCTION

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_loan_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialToolbar>(R.id.toolbar).title =
            "" + view.findViewById<MaterialToolbar>(R.id.toolbar).title + Constants.INSTRUCTION_TITLE

        view.findViewById<TextView>(R.id.amount).text = context?.getString(R.string.amount_example)
        view.findViewById<TextView>(R.id.date).text =
            SimpleDateFormat(
                Constants.DATE_FORMAT_PATTERN,
                Locale(Constants.LANGUAGE)
            ).format(Date())
        view.findViewById<TextView>(R.id.firstName).text = context?.getString(R.string.name_example)
        view.findViewById<TextView>(R.id.lastName).text = context?.getString(R.string.last_name_example)
        view.findViewById<TextView>(R.id.percent).text = context?.getString(R.string.percent_example)
        view.findViewById<TextView>(R.id.period).text = context?.getString(R.string.period_example)
        view.findViewById<TextView>(R.id.phoneNumber).text = context?.getString(R.string.phone_number_example)
        view.findViewById<TextView>(R.id.state).text = State.APPROVED.toString()

    }
}