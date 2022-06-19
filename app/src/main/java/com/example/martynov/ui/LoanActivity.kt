package com.example.martynov.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.martynov.R
import com.example.martynov.ui.fragments.LoanHistoryFragment
import com.example.martynov.utils.Constants

class LoanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, LoanHistoryFragment.newInstance())
                .addToBackStack(Constants.ADD_LOAN_HISTORY_FRAGMENT)
                .commit()
    }
}