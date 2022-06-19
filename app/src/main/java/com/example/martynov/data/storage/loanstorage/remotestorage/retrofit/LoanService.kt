package com.example.martynov.data.storage.loanstorage.remotestorage.retrofit

import com.example.martynov.data.model.LoanConditionsDTO
import com.example.martynov.data.model.LoanDTO
import com.example.martynov.data.model.NewLoanDTO
import retrofit2.Call
import retrofit2.http.*

interface LoanService {

    @GET("loans/all")
    fun getAllLoans(
        @Header("Authorization") authorizationToken: String
    ): Call<List<LoanDTO>>

    @GET("loans/{id}")
    fun getLoanDetail(
        @Header("Authorization") authorizationToken: String,
        @Path("id") id: Int
    ): Call<LoanDTO>

    @GET("loans/conditions")
    fun getLoanConditions(
        @Header("Authorization") authorizationToken: String
    ): Call<LoanConditionsDTO>

    @POST("loans")
    fun createNewLoan(
        @Header("Authorization") authorizationToken: String,
        @Body body: NewLoanDTO
    ): Call<LoanDTO>
}