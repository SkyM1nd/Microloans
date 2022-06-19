package com.example.martynov.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.martynov.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreateNewLoanInstructionFragment : BaseInstructionFragment() {

    companion object {
        fun newInstance(): CreateNewLoanInstructionFragment =
            CreateNewLoanInstructionFragment().apply { }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_loan_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataInRecyclerView(view, listItem)

        view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh).foreground =
            ContextCompat.getDrawable(requireContext(), R.drawable.shadow_box)

        view.findViewById<FloatingActionButton>(R.id.newLoanButton).apply {
            isEnabled = true
        }
    }
}