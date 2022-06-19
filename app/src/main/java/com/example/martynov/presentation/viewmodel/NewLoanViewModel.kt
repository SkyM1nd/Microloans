package com.example.martynov.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.martynov.domain.entity.LoanConditionsEntity
import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.domain.entity.NewLoanEntity
import com.example.martynov.domain.entity.RepositoryResult
import com.example.martynov.domain.usecase.CreateNewLoanUseCase
import com.example.martynov.domain.usecase.GetLoanConditionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewLoanViewModel(
    private val getLoanConditionsUseCase: GetLoanConditionsUseCase,
    private val createNewLoanUseCase: CreateNewLoanUseCase
) : BaseViewModel() {

    private val _amount = MutableLiveData<String>()
    val amount: LiveData<String> = _amount

    private val _period = MutableLiveData<String>()
    val period: LiveData<String> = _period

    private val _percent = MutableLiveData<String>()
    val percent: LiveData<String> = _percent

    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String> = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> = _lastName

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _resultGetLoanConditions = SingleLiveEvent<RepositoryResult<LoanConditionsEntity>>()
    val resultGetLoanConditions: LiveData<RepositoryResult<LoanConditionsEntity>> =
        _resultGetLoanConditions

    private val _resultCreateNewLoan = SingleLiveEvent<RepositoryResult<LoanEntity>>()
    val resultCreateNewLoan: LiveData<RepositoryResult<LoanEntity>> = _resultCreateNewLoan

    fun setAmount(value: String) {
        _amount.value = value
    }

    fun setPeriod(value: String) {
        _period.value = value
    }

    fun setPercent(value: String) {
        _percent.value = value
    }

    fun setFirstName(value: String) {
        _firstName.value = value
    }

    fun setLastName(value: String) {
        _lastName.value = value
    }

    fun setPhoneNumber(value: String) {
        _phoneNumber.value = value
    }

    fun getLoanConditions() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            getLoanConditionsUseCase().collect {
                _resultGetLoanConditions.postValue(it)
            }
        }
    }

    fun createNewLoan(newLoanEntity: NewLoanEntity) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            createNewLoanUseCase(newLoanEntity).collect {
                _resultCreateNewLoan.postValue(it)
            }
        }
    }
}