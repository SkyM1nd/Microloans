package com.example.martynov.ui.fragment

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.martynov.R
import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.utils.Constants
import java.util.*

class LoanDetailFragment : Fragment(R.layout.fragment_loan_detail) {

    companion object {
        fun newInstance(loanEntity: LoanEntity): LoanDetailFragment =
            LoanDetailFragment().apply { item = loanEntity }
    }

    var item: LoanEntity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loanEntity = requireNotNull(item)

        view.findViewById<TextView>(R.id.amount).text = loanEntity.amount.toString()
        view.findViewById<TextView>(R.id.date).text =
            SimpleDateFormat(Constants.DATE_FORMAT_PATTERN, Locale(Constants.LANGUAGE)).format(
                loanEntity.date
            )
        view.findViewById<TextView>(R.id.firstName).text = loanEntity.firstName
        view.findViewById<TextView>(R.id.lastName).text = loanEntity.lastName
        view.findViewById<TextView>(R.id.percent).text = loanEntity.percent.toString()
        view.findViewById<TextView>(R.id.period).text = loanEntity.period.toString()
        view.findViewById<TextView>(R.id.phoneNumber).text = loanEntity.phoneNumber
        view.findViewById<TextView>(R.id.state).text = loanEntity.state.toString()
    }
}