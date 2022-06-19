package com.example.martynov.domain.usecase

import com.example.martynov.domain.repository.LoanRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val loanRepository: LoanRepository) {

    operator fun invoke(): String =
        loanRepository.getToken()
}
