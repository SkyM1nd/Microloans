package com.example.martynov.domain.usecase

import com.example.martynov.domain.repository.LoanRepository
import com.example.martynov.domain.repository.LoginRepository
import javax.inject.Inject

class DeleteTokenUseCase @Inject constructor(private val loanRepository: LoanRepository) {

    operator fun invoke() {
        loanRepository.deleteToken()
    }
}