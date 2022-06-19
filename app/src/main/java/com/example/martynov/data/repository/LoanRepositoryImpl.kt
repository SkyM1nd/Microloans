package com.example.martynov.data.repository

import com.example.martynov.data.mapper.*
import com.example.martynov.data.storage.loanstorage.localstorage.LoanLocalStorage
import com.example.martynov.data.storage.loanstorage.remotestorage.LoanRemoteStorage
import com.example.martynov.domain.entity.LoanConditionsEntity
import com.example.martynov.domain.entity.LoanEntity
import com.example.martynov.domain.entity.NewLoanEntity
import com.example.martynov.domain.entity.RepositoryResult
import com.example.martynov.domain.repository.LoanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoanRepositoryImpl @Inject constructor(
    private val loanLocalStorage: LoanLocalStorage,
    private val loanRemoteStorage: LoanRemoteStorage
) : LoanRepository {

    override fun getAllLoans(): Flow<RepositoryResult<List<LoanEntity>>> {
        val token = getToken()
        return loanRemoteStorage.getAllLoans(token).map { result ->
            MapFlowResult.mapFlowResult(result) { MapToListLoanEntity(it) }
        }
    }

    override fun getLoanDetail(loanId: Int): Flow<RepositoryResult<LoanEntity>> {
        val token = getToken()
        return loanRemoteStorage.getLoanDetail(token, loanId).map { result ->
            MapFlowResult.mapFlowResult(result) { MapToLoanEntity(it) }
        }
    }

    override fun getLoanConditions(): Flow<RepositoryResult<LoanConditionsEntity>> {
        val token = getToken()
        return loanRemoteStorage.getLoanConditions(token).map { result ->
            MapFlowResult.mapFlowResult(result) { MapToLoanConditionsEntity(it) }
        }
    }

    override fun createNewLoan(
        newLoanEntity: NewLoanEntity
    ): Flow<RepositoryResult<LoanEntity>> {
        val token = getToken()
        val newLoanDTO = MapToNewLoanDTO(newLoanEntity)
        return loanRemoteStorage.createNewLoan(token, newLoanDTO).map { result ->
            MapFlowResult.mapFlowResult(result) { MapToLoanEntity(it) }
        }
    }

    override fun deleteToken() = loanLocalStorage.deleteToken()

    override fun getToken(): String = requireNotNull(loanLocalStorage.getToken())

    override fun setNotFirstLogin() = loanLocalStorage.setNotFirstLogin()
}