package com.example.martynov .presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.martynov.domain.entity.RepositoryResult
import com.example.martynov.domain.entity.UserEntity
import com.example.martynov.domain.usecase.*
import com.example.martynov.utils.Screen
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val router: Router,
    private val loginUseCase: LoginUseCase,
    private val registrationUseCase: RegistrationUseCase,
    private val reentryUseCase: ReentryUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val isFirstLoginUseCase: IsFirstLoginUseCase
) : BaseViewModel() {

    private val _resultLogin = SingleLiveEvent<RepositoryResult<String>>()
    val resultLogin: LiveData<RepositoryResult<String>> = _resultLogin

    private val _resultRegistration = SingleLiveEvent<RepositoryResult<String>>()
    val resultRegistration: LiveData<RepositoryResult<String>> = _resultRegistration

    private val _errorEmptyFields = SingleLiveEvent(false)
    val errorEmptyFields: LiveData<Boolean> = _errorEmptyFields

    private val _name = MutableLiveData<String>(null)
    private val _password = MutableLiveData<String>()

    fun isFirstLogin(): Boolean = isFirstLoginUseCase()

    fun login() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            if (validFields()) {
                val userEntity = UserEntity(_name.value!!, _password.value!!)
                loginUseCase(userEntity).collect {
                    _resultLogin.postValue(it)
                }
            } else {
                _errorEmptyFields.postValue(true)
            }
        }
    }

    fun registration() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            if (validFields()) {
                val userEntity = UserEntity(_name.value!!, _password.value!!)
                registrationUseCase(userEntity).collect {
                    _resultRegistration.postValue(it)
                }
            } else {
                _errorEmptyFields.postValue(true)
            }
        }
    }

    private fun validFields(): Boolean =
        _name.value?.isNotEmpty() ?: false && _password.value?.isNotEmpty() ?: false

    fun reentry() {
        val token = reentryUseCase()

        if (token != null) {
            navigateToLoan()
        }
    }

    fun saveToken(token: String) {
        saveTokenUseCase(token)
    }

    fun setName(text: String) {
        _name.value = text
    }

    fun setPassword(text: String) {
        _password.value = text
    }

    fun navigateToLoan() {
        router.navigateTo(Screen.loan())
    }

    fun navigateToInstruction() {
        router.navigateTo(Screen.instruction())
    }
}