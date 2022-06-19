package com.example.martynov.domain.usecase

import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.domain.entity.NewLoanEntity
import com.example.martynov.domain.entity.RepositoryResult
import com.example.martynov.domain.repository.LoanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateNewLoanUseCase @Inject constructor(private val loanRepository: LoanRepository) {

    operator fun invoke(
        newLoanEntity: NewLoanEntity
    ): Flow<RepositoryResult<LoanEntity>> =
        loanRepository.createNewLoan(newLoanEntity)
}
