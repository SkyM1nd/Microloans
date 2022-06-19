package com.example.martynov.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.domain.usecase.DeleteTokenUseCase
import com.example.martynov.domain.usecase.GetLoanDetailUseCase
import com.example.martynov.domain.usecase.GetLoanHistoryUseCase
import com.example.martynov.domain.usecase.SetNotFirstLoginUseCase

class LoanViewModelFactory(
    private val getLoanHistoryUseCase: GetLoanHistoryUseCase,
    private val getLoanDetailUseCase: GetLoanDetailUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase,
    private val setNotFirstLoginUseCase: SetNotFirstLoginUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoanViewModel(
            getLoanHistoryUseCase,
            getLoanDetailUseCase,
            deleteTokenUseCase,
            setNotFirstLoginUseCase
        ) as T
    }
}