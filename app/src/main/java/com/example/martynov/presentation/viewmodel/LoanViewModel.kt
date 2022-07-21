package com.example.martynov.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.domain.entity.RepositoryResult
import com.example.martynov.domain.usecase.DeleteTokenUseCase
import com.example.martynov.domain.usecase.GetLoanDetailUseCase
import com.example.martynov.domain.usecase.GetLoanHistoryUseCase
import com.example.martynov.domain.usecase.SetNotFirstLoginUseCase
import com.example.martynov.utils.Screen
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoanViewModel(
    private val router: Router,
    private val getLoanHistoryUseCase: GetLoanHistoryUseCase,
    private val getLoanDetailUseCase: GetLoanDetailUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase,
    private val setNotFirstLoginUseCase: SetNotFirstLoginUseCase
) : BaseViewModel() {

    private val _loans = MutableLiveData<List<LoanEntity>>()
    val loans: LiveData<List<LoanEntity>> = _loans

    private val _resultGetAllLoans = SingleLiveEvent<RepositoryResult<List<LoanEntity>>>()
    val resultGetAllLoans: LiveData<RepositoryResult<List<LoanEntity>>> = _resultGetAllLoans

    private val _resultGetLoanDetail = SingleLiveEvent<RepositoryResult<LoanEntity>>()
    val resultGetLoanDetail: LiveData<RepositoryResult<LoanEntity>> = _resultGetLoanDetail

    fun setNotFirstLogin() {
        setNotFirstLoginUseCase()
    }

    fun setLoans(data: List<LoanEntity>) {
        _loans.value = data
    }

    fun getAllLoans() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            getLoanHistoryUseCase().collect {
                _resultGetAllLoans.postValue(it)
            }
        }
    }

    fun getLoanDetail(loanId: Int) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            getLoanDetailUseCase(loanId).collect {
                _resultGetLoanDetail.postValue(it)
            }
        }
    }

    fun deleteToken() {
        deleteTokenUseCase()
    }

    fun navigateToNewLoan() {
        router.navigateTo(Screen.newLoan())
    }

    fun navigateToInstruction() {
        router.navigateTo(Screen.instruction())
    }

    fun navigateToLogin() {
        router.navigateTo(Screen.login())
    }

    fun navigateToDetail(loanEntity: LoanEntity) {
        router.navigateTo(Screen.detail(loanEntity))
    }
}