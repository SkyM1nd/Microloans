package com.example.martynov.presentation

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.martynov.R
import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.utils.Constants
import java.util.*

class LoanAdapter(private val onClick: (LoanEntity) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val LOAN_VIEW_TYPE = 0
    }

    var loans: List<LoanEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            LOAN_VIEW_TYPE -> LoanViewHolder(parent, onClick)
            else -> throw IllegalArgumentException()
        }

    override fun getItemCount(): Int =
        loans.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LoanViewHolder -> holder.bind(loans[position])
        }
    }

    override fun getItemViewType(position: Int): Int =
        LOAN_VIEW_TYPE
}

class LoanViewHolder(parent: ViewGroup, private val onClick: (LoanEntity) -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_loan, parent, false)
    ) {

    fun bind(item: LoanEntity) {
        (itemView as ConstraintLayout).findViewById<TextView>(R.id.date).text =
            SimpleDateFormat(
                Constants.DATE_FORMAT_PATTERN,
                Locale(Constants.LANGUAGE)
            ).format(item.date)
        itemView.findViewById<TextView>(R.id.amount).text =
            item.amount.toString()
        itemView.findViewById<TextView>(R.id.state).text =
            item.state.toString()
        itemView.findViewById<ImageView>(R.id.detail).setOnClickListener { onClick(item) }
    }
}
