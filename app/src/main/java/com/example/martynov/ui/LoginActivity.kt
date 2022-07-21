package com.example.martynov.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.R
import com.example.martynov.app.App
import com.example.martynov.domain.entity.UserEntity
import com.example.martynov.presentation.HandlerResult
import com.example.martynov.presentation.viewmodel.LoginViewModel
import com.example.martynov.presentation.viewmodel.LoginViewModelFactory
import com.example.martynov.ui.snackbar.LoginSnackbar
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = AppNavigator(this, -1)

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, loginViewModelFactory)[LoginViewModel::class.java]
    }

    private val myCoordinatorLayout: CoordinatorLayout by lazy { findViewById(R.id.loginCoordinatorLayout) }
    private val loginSnackbar: LoginSnackbar by lazy { LoginSnackbar(myCoordinatorLayout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (applicationContext as App).appComponent.inject(this)

        if (savedInstanceState == null) {
            loginViewModel.reentry()
        }

        initListener()
        initObserve()
    }

    private fun initListener() {
        val name = findViewById<EditText>(R.id.editTextUserName)
        name.addTextChangedListener {
            loginViewModel.setName(name.text.toString())
        }

        val password = findViewById<EditText>(R.id.editTextUserPassword)
        password.addTextChangedListener {
            loginViewModel.setPassword(password.text.toString())
        }

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            loginViewModel.login()
        }

        findViewById<Button>(R.id.registrationButton).setOnClickListener {
            loginViewModel.registration()
        }
    }

    private fun initObserve() {
        loginViewModel.error.observe(this) {
            loginViewModel.setProgressBarVisible(false)
            loginSnackbar.showSnackbarToUnknownError()
        }

        loginViewModel.errorEmptyFields.observe(this) {
            loginViewModel.setProgressBarVisible(false)
            loginSnackbar.showSnackbarToEmptyFields()
        }

        loginViewModel.resultLogin.observe(this) { result ->
            object : HandlerResult(this, loginViewModel, loginSnackbar) {
                override fun resultSuccess() {
                    loginViewModel.setProgressBarVisible(false)
                    loginViewModel.saveToken(requireNotNull(result.data))
                    if (loginViewModel.isFirstLogin()) {
                        loginViewModel.navigateToInstruction()
                    } else {
                        loginViewModel.navigateToLoan()
                    }
                }
            }(result)
        }

        loginViewModel.resultRegistration.observe(this) { result ->
            object : HandlerResult(this, loginViewModel, loginSnackbar) {
                override fun resultSuccess() {
                    loginViewModel.setProgressBarVisible(false)
                    loginSnackbar.showSnackbarSuccessRegistration()
                }
            }(result)
        }

        loginViewModel.progressBarVisible.observe(this) {
            findViewById<ProgressBar>(R.id.loginProgressBar).isVisible = it
        }
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