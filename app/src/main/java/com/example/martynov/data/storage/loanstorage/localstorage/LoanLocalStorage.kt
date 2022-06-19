package com.example.martynov.data.storage.loanstorage.localstorage

interface LoanLocalStorage {

    fun getToken(): String?

    fun deleteToken()

    fun setNotFirstLogin()
}