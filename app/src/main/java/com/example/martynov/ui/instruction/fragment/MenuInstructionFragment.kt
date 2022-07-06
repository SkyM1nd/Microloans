package com.example.martynov.ui.instruction.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.martynov.R
import com.example.martynov.ui.instruction.InstructionConstants
import com.google.android.material.appbar.MaterialToolbar

class MenuInstructionFragment : BaseHistoryInstructionFragment() {

    override val tabName = InstructionConstants.MENU_HISTORY_TAB_NAME
    override val description = InstructionConstants.MENU_HISTORY_INSTRUCTION

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
    }

    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<ImageView>(R.id.menuPointer).isVisible = true
        view?.findViewById<MaterialToolbar>(R.id.toolbar)?.showOverflowMenu()
    }

    override fun onPause() {
        super.onPause()
        requireActivity().findViewById<ImageView>(R.id.menuPointer).isVisible = false
    }
}