package com.example.martynov.data.storage.loginstorage.localstorage

interface LoginLocalStorage {

    fun saveToken(token: String)

    fun getToken(): String?

    fun isFirstLogin(): Boolean
}