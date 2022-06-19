package com.example.martynov.ui.instruction

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.martynov.R
import com.example.martynov.presentation.instruction.InstructionViewModel
import com.example.martynov.ui.LoanActivity
import com.example.martynov.utils.Constants
import com.google.android.material.tabs.TabLayout


class InstructionActivity : AppCompatActivity() {

    private val instructionViewModel: InstructionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.fragmentContainer,
                HistoryInstructionFragment.newInstance()
            )
                .addToBackStack(Constants.ADD_LOAN_HISTORY_FRAGMENT)
                .commit()

            findViewById<TextView>(R.id.helpTextView).text =
                Constants.HISTORY_INSTRUCTION
        }
        findViewById<TabLayout>(R.id.instructionTabLayout).addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                clearVisible()
                when (tab?.text) {
                    resources.getString(R.string.history) -> {
                        findViewById<TextView>(R.id.helpTextView).text =
                            Constants.HISTORY_INSTRUCTION
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.fragmentContainer,
                                HistoryInstructionFragment.newInstance()
                            )
                            .addToBackStack(Constants.ADD_LOAN_HISTORY_FRAGMENT)
                            .commit()
                    }
                    resources.getString(R.string.update_history) -> {
                        findViewById<TextView>(R.id.helpTextView).text =
                            Constants.UPDATE_HISTORY_INSTRUCTION

                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.fragmentContainer,
                                UpdateInstructionFragment.newInstance()
                            )
                            .addToBackStack(Constants.ADD_LOAN_HISTORY_FRAGMENT)
                            .commit()
                    }
                    resources.getString(R.string.menu) -> {
                        findViewById<ImageView>(R.id.menuPointer).isVisible = true
                        findViewById<TextView>(R.id.helpTextView).text =
                            Constants.MENU_HISTORY_INSTRUCTION

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, MenuInstructionFragment.newInstance())
                            .addToBackStack(Constants.ADD_LOAN_HISTORY_FRAGMENT)
                            .commit()
                    }
                    resources.getString(R.string.open_detail_loan) -> {
                        findViewById<TextView>(R.id.helpTextView).text =
                            Constants.OPEN_DETAIL_INSTRUCTION
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.fragmentContainer,
                                OpenDetailInstructionFragment.newInstance()
                            )
                            .addToBackStack(Constants.ADD_LOAN_HISTORY_FRAGMENT)
                            .commit()
                    }
                    resources.getString(R.string.detail) -> {
                        findViewById<TextView>(R.id.helpTextView).text =
                            Constants.DETAIL_INSTRUCTION
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.fragmentContainer,
                                DetailInstructionFragment.newInstance()
                            )
                            .addToBackStack(Constants.ADD_LOAN_DETAIL_FRAGMENT)
                            .commit()
                    }
                    resources.getString(R.string.create_new_loan) -> {
                        findViewById<TextView>(R.id.helpTextView).text =
                            Constants.CREATE_NEW_LOAN_INSTRUCTION
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.fragmentContainer,
                                CreateNewLoanInstructionFragment.newInstance()
                            )
                            .addToBackStack(Constants.ADD_LOAN_HISTORY_FRAGMENT)
                            .commit()
                    }
                    resources.getString(R.string.conditions) -> {
                        findViewById<TextView>(R.id.helpTextView).text =
                            Constants.CONDITIONS_INSTRUCTION
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.fragmentContainer,
                                ConditionsInstructionFragment.newInstance()
                            )
                            .addToBackStack(Constants.ADD_NEW_LOAN_FRAGMENT)
                            .commit()
                    }
                    resources.getString(R.string.fill_fields) -> {
                        findViewById<TextView>(R.id.helpTextView).text =
                            Constants.FILL_FIELDS_INSTRUCTION
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.fragmentContainer,
                                FillFieldsInstructionFragment.newInstance()
                            )
                            .addToBackStack(Constants.ADD_NEW_LOAN_FRAGMENT)
                            .commit()
                    }
                    resources.getString(R.string.new_loan) -> {
                        findViewById<TextView>(R.id.helpTextView).text =
                            Constants.NEW_LOAN_INSTRUCTION
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.fragmentContainer,
                                NewLoanInstructionFragment.newInstance()
                            )
                            .addToBackStack(Constants.ADD_NEW_LOAN_FRAGMENT)
                            .commit()
                    }
                    resources.getString(R.string.success_loan) -> {
                        findViewById<TextView>(R.id.helpTextView).text =
                            Constants.SUCCESS_LOAN_INSTRUCTION
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.fragmentContainer,
                                SuccessLoanCreateInstructionFragment.newInstance()
                            )
                            .addToBackStack(Constants.ADD_SUCCESS_LOAN_CREATE_FRAGMENT)
                            .commit()
                    }
                    resources.getString(R.string.end) -> {
                        findViewById<TextView>(R.id.helpTextView).text =
                            Constants.END_INSTRUCTION
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.fragmentContainer,
                                EndInstructionFragment.newInstance()
                            )
                            .addToBackStack(Constants.ADD_SUCCESS_LOAN_CREATE_FRAGMENT)
                            .commit()
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val intent = Intent(this@InstructionActivity, LoanActivity::class.java)
                    startActivity(intent)
                }
            })
    }

    fun clearVisible() {
        instructionViewModel.stopAnimatorSet()

        findViewById<ImageView>(R.id.menuPointer).isVisible = false
        findViewById<ConstraintLayout>(R.id.popupWindowConditions).isVisible = false

        val myLayout: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        myLayout.gravity = Gravity.CENTER
        findViewById<ImageView>(R.id.touchHand).apply {
            alpha = 0f
            layoutParams = myLayout
        }
    }
}