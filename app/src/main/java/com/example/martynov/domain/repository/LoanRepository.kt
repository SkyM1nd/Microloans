package com.example.martynov.domain.repository

import com.example.martynov.domain.entity.LoanConditionsEntity
import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.domain.entity.NewLoanEntity
import com.example.martynov.domain.entity.RepositoryResult
import kotlinx.coroutines.flow.Flow

interface LoanRepository {

    fun getAllLoans(): Flow<RepositoryResult<List<LoanEntity>>>

    fun getLoanDetail(loanId: Int): Flow<RepositoryResult<LoanEntity>>

    fun createNewLoan(newLoanEntity: NewLoanEntity): Flow<RepositoryResult<LoanEntity>>

    fun getLoanConditions(): Flow<RepositoryResult<LoanConditionsEntity>>

    fun deleteToken()

    fun getToken(): String

    fun setNotFirstLogin()
}