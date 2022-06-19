package com.example.martynov.ui.instruction

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.martynov.R
import com.example.martynov.domain.entity.State
import com.example.martynov.utils.Constants
import com.google.android.material.appbar.MaterialToolbar
import java.util.*

class DetailInstructionFragment : Fragment() {

    companion object {
        fun newInstance(): DetailInstructionFragment =
            DetailInstructionFragment().apply { }
    }

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

        view.findViewById<TextView>(R.id.amount).text = "7000"
        view.findViewById<TextView>(R.id.date).text =
            SimpleDateFormat(
                Constants.DATE_FORMAT_PATTERN,
                Locale(Constants.LANGUAGE)
            ).format(Date())
        view.findViewById<TextView>(R.id.firstName).text = "Иван"
        view.findViewById<TextView>(R.id.lastName).text = "Иванов"
        view.findViewById<TextView>(R.id.percent).text = "5.6"
        view.findViewById<TextView>(R.id.period).text = "10"
        view.findViewById<TextView>(R.id.phoneNumber).text = "+88005553535"
        view.findViewById<TextView>(R.id.state).text = State.APPROVED.toString()

    }
}