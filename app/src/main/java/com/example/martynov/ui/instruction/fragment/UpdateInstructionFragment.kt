package com.example.martynov.ui.instruction.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.martynov.R
import com.example.martynov.presentation.instruction.InstructionViewModel
import com.example.martynov.ui.instruction.InstructionConstants

class UpdateInstructionFragment : BaseHistoryInstructionFragment() {

    override val tabName = InstructionConstants.UPDATE_HISTORY_TAB_NAME
    override val description = InstructionConstants.UPDATE_HISTORY_INSTRUCTION

    private val instructionViewModel: InstructionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_loan_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataInRecyclerView(
            view,
            listOf(listItem[0])
        )

        instructionViewModel.historyAnimStepTwo.observe(viewLifecycleOwner) {
            view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh).isRefreshing = true
        }

        instructionViewModel.historyAnimStepThree.observe(viewLifecycleOwner) {
            view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh).isRefreshing = false
            setDataInRecyclerView(view, listItem)
        }

        instructionViewModel.historyAnimStepFour.observe(viewLifecycleOwner) {
            setDataInRecyclerView(view, listOf(listItem[0]))
        }
    }

    private fun startAnimation() {

        val touchHand = requireActivity().findViewById<ImageView>(R.id.touchHand)

        val anim1 = ObjectAnimator.ofFloat(touchHand, View.ALPHA, 100F).apply {
            duration = 400L
            interpolator = LinearInterpolator()

        }

        val anim2 = ObjectAnimator.ofFloat(touchHand, View.TRANSLATION_Y, 150F).apply {
            duration = 700L
            interpolator = LinearInterpolator()
        }

        val anim3 = ObjectAnimator.ofFloat(touchHand, View.ALPHA, 0F).apply {
            duration = 1000L
            interpolator = LinearInterpolator()
        }

        val anim4 = ObjectAnimator.ofFloat(touchHand, View.TRANSLATION_Y, -150F).apply {
            duration = 1200L
            interpolator = LinearInterpolator()
        }

        instructionViewModel.startAnimatorSet(anim1, anim2, anim3, anim4)
    }

    override fun onResume() {
        super.onResume()
        startAnimation()
    }

    override fun onPause() {
        super.onPause()
        instructionViewModel.stopAnimatorSet()

        val myLayout: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        myLayout.gravity = Gravity.CENTER
        requireActivity().findViewById<ImageView>(R.id.touchHand).apply {
            alpha = 0f
            layoutParams = myLayout
        }
    }
}