package com.example.martynov.ui.instruction

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.martynov.R
import com.example.martynov.app.App
import com.example.martynov.utils.Screen
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class InstructionActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = AppNavigator(this, -1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)

        (applicationContext as App).appComponent.inject(this)

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

                findViewById<ConstraintLayout>(R.id.popupWindowConditions).isVisible = false
                findViewById<TextView>(R.id.helpTextView).text =
                    adapter.fragments[position].description
            }
        })
    }

    override fun onBackPressed() {
        router.navigateTo(Screen.loan())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}