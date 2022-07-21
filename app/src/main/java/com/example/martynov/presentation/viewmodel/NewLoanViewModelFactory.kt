package com.example.martynov.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.domain.usecase.CreateNewLoanUseCase
import com.example.martynov.domain.usecase.GetLoanConditionsUseCase
import com.github.terrakok.cicerone.Router

class NewLoanViewModelFactory(
    private val router: Router,
    private val getLoanConditionsUseCase: GetLoanConditionsUseCase,
    private val createNewLoanUseCase: CreateNewLoanUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewLoanViewModel(
            router,
            getLoanConditionsUseCase,
            createNewLoanUseCase
        ) as T
    }
}