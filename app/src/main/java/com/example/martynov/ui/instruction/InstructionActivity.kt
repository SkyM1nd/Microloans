package com.example.martynov.ui.instruction

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.martynov.R
import com.example.martynov.ui.LoanActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class InstructionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)

        val adapter = ViewPagerFragmentStateAdapter(this)

        val viewPager = findViewById<ViewPager2>(R.id.fragmentContainer)
        viewPager.adapter = adapter

        val tabLayout = findViewById<TabLayout>(R.id.instructionTabLayout)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.fragments[position].tabName
        }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                findViewById<TextView>(R.id.helpTextView).text =
                    adapter.fragments[position].description
            }
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
}