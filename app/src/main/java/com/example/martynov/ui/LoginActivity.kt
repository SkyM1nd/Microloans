package com.example.martynov.ui

import android.content.Intent
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
import com.example.martynov.ui.instruction.InstructionActivity
import com.example.martynov.ui.snackbar.LoginSnackbar
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (applicationContext as App).appComponent.inject(this)

        val myCoordinatorLayout = findViewById<CoordinatorLayout>(R.id.loginCoordinatorLayout)
        val loginSnackbar = LoginSnackbar(myCoordinatorLayout)

        if (savedInstanceState == null) {
            val token = loginViewModel.reentry()
            if (token != null) {
                startLoanActivity()
            }
        }

        loginViewModel.error.observe(this) {
            loginViewModel.setProgressBarVisible(false)
            loginSnackbar.showSnackbarToUnknownError()
        }

        loginViewModel.resultLogin.observe(this) { result ->
            object : HandlerResult(this, loginViewModel, loginSnackbar) {
                override fun resultSuccess() {
                    loginViewModel.setProgressBarVisible(false)
                    loginViewModel.saveToken(requireNotNull(result.data))
                    if (loginViewModel.isFirstLogin()) {
                        startInstructionActivity()
                    } else {
                        startLoanActivity()
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

        val name = findViewById<EditText>(R.id.editTextUserName)
        name.addTextChangedListener {
            loginViewModel.setName(name.text.toString())
        }

        val password = findViewById<EditText>(R.id.editTextUserPassword)
        password.addTextChangedListener {
            loginViewModel.setPassword(password.text.toString())
        }

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            if (name.text.isNotEmpty() and password.text.isNotEmpty()) {
                loginViewModel.login(UserEntity(name.text.toString(), password.text.toString()))
            } else {
                loginSnackbar.showSnackbarToEmptyFields()
            }
        }

        findViewById<Button>(R.id.registrationButton).setOnClickListener {
            if (name.text.isNotEmpty() and password.text.isNotEmpty()) {
                loginViewModel.registration(
                    UserEntity(
                        name.text.toString(),
                        password.text.toString()
                    )
                )
            } else {
                loginSnackbar.showSnackbarToEmptyFields()
            }
        }
    }

    private fun startLoanActivity() {
        val intent = Intent(this, LoanActivity::class.java)
        startActivity(intent)
    }

    private fun startInstructionActivity() {
        val intent = Intent(this, InstructionActivity::class.java)
        startActivity(intent)
    }
}