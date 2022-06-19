package com.example.martynov.data.storage.loanstorage.remotestorage

import com.example.martynov.data.model.LoanConditionsDTO
import com.example.martynov.data.model.LoanDTO
import com.example.martynov.data.model.NewLoanDTO
import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.domain.entity.RepositoryResult
import kotlinx.coroutines.flow.Flow

interface LoanRemoteStorage {

    fun getAllLoans(token: String): Flow<RepositoryResult<List<LoanDTO>>>

    fun getLoanDetail(token: String, loanId: Int): Flow<RepositoryResult<LoanDTO>>

    fun getLoanConditions(token: String): Flow<RepositoryResult<LoanConditionsDTO>>

    fun createNewLoan(token: String, newLoanDTO: NewLoanDTO): Flow<RepositoryResult<LoanDTO>>
}