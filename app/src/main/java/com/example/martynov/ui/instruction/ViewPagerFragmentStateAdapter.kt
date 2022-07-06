package com.example.martynov.ui.instruction

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.martynov.ui.instruction.fragment.*

class ViewPagerFragmentStateAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    val fragments = listOf(
        HistoryInstructionFragment(),
        UpdateInstructionFragment(),
        MenuInstructionFragment(),
        OpenDetailInstructionFragment(),
        DetailInstructionFragment(),
        CreateNewLoanInstructionFragment(),
        ConditionsInstructionFragment(),
        FillFieldsInstructionFragment(),
        NewLoanInstructionFragment(),
        SuccessLoanCreateInstructionFragment(),
        EndInstructionFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}