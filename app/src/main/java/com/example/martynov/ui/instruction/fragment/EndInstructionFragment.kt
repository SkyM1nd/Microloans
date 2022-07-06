package com.example.martynov.ui.instruction.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.martynov.R
import com.example.martynov.ui.LoanActivity
import com.example.martynov.ui.instruction.InstructionConstants

class EndInstructionFragment : BaseInstructionFragment() {

    override val tabName = InstructionConstants.END_TAB_NAME
    override val description = InstructionConstants.END_INSTRUCTION

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_end_instruction, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.smileImageView).setOnClickListener {
            val intent = Intent(context, LoanActivity::class.java)
            requireContext().startActivity(intent)
        }
    }
}