package com.example.martynov.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.martynov.domain.entity.RepositoryResult
import com.example.martynov.domain.entity.UserEntity
import com.example.martynov.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
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

    private val _name = MutableLiveData<String>(null)
    val name: LiveData<String> = _name

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    fun isFirstLogin(): Boolean = isFirstLoginUseCase()

    fun login(userEntity: UserEntity) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            loginUseCase(userEntity).collect {
                _resultLogin.postValue(it)
            }
        }
    }

    fun reentry(): String? =
        reentryUseCase()

    fun registration(userEntity: UserEntity) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            registrationUseCase(userEntity).collect {
                _resultRegistration.postValue(it)
            }
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
}