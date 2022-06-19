package com.example.martynov.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.domain.usecase.CreateNewLoanUseCase
import com.example.martynov.domain.usecase.GetLoanConditionsUseCase

class NewLoanViewModelFactory(
    private val getLoanConditionsUseCase: GetLoanConditionsUseCase,
    private val createNewLoanUseCase: CreateNewLoanUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewLoanViewModel(
            getLoanConditionsUseCase,
            createNewLoanUseCase
        ) as T
    }
}