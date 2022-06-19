package com.example.martynov.data.storage.loanstorage.remotestorage.retrofit

import com.example.martynov.data.model.LoanConditionsDTO
import com.example.martynov.data.model.LoanDTO
import com.example.martynov.data.model.NewLoanDTO
import com.example.martynov.data.storage.BaseRemoteStorage
import com.example.martynov.data.storage.loanstorage.remotestorage.LoanRemoteStorage
import com.example.martynov.domain.entity.RepositoryResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import javax.inject.Inject

//TODO Дописать случаи всех кодов

class LoanApi @Inject constructor(private val retrofit: Retrofit) : LoanRemoteStorage,
    BaseRemoteStorage() {

    private val api: LoanService = retrofit.create(LoanService::class.java)

    override fun getAllLoans(token: String): Flow<RepositoryResult<List<LoanDTO>>> {
        val call = api.getAllLoans(token)

        return doRequest(call)
    }

    override fun getLoanDetail(token: String, loanId: Int): Flow<RepositoryResult<LoanDTO>> {
        val call = api.getLoanDetail(token, loanId)

        return doRequest(call)
    }

    override fun getLoanConditions(token: String): Flow<RepositoryResult<LoanConditionsDTO>> {
        val call = api.getLoanConditions(token)

        return doRequest(call)
    }

    override fun createNewLoan(
        token: String,
        newLoanDTO: NewLoanDTO
    ): Flow<RepositoryResult<LoanDTO>> {
        val call = api.createNewLoan(token, newLoanDTO)

        return doRequest(call)
    }
}