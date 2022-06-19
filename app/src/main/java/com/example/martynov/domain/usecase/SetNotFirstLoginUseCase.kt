package com.example.martynov.domain.usecase

import com.example.martynov.domain.repository.LoanRepository
import javax.inject.Inject

class SetNotFirstLoginUseCase @Inject constructor(private val loanRepository: LoanRepository) {

    operator fun invoke() = loanRepository.setNotFirstLogin()
}