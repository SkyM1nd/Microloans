package com.example.martynov.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.martynov.R
import com.example.martynov.app.App
import com.example.martynov.utils.Screen
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class LoanActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = AppNavigator(this, R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan)

        (applicationContext as App).appComponent.inject(this)

        if (savedInstanceState == null)
            router.navigateTo(Screen.history())
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