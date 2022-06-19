package com.example.martynov.domain.entity

import java.io.Serializable
import java.util.*

class LoanEntity(
    val amount: Int,
    val date: Date,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
    val state: State
) : Serializable

enum class State {
    APPROVED, REGISTERED, REJECTED
}